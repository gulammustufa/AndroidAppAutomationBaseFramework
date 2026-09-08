package utility;

import com.steps.cucumber.BaseSteps;
import utility.enums.DeviceOwners;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class DeviceManager extends BaseSteps {
    private static final ConcurrentLinkedQueue<DeviceOwners> availableDevices = new ConcurrentLinkedQueue<>();
    private static final Set<DeviceOwners> inUseDevices = Collections.synchronizedSet(new HashSet<>());
    private static DeviceManager instance;
    public static final Map<DeviceOwners, DeviceInfo> DEVICE_INFO_MAP = new HashMap<>();

    static {
        DEVICE_INFO_MAP.put(DeviceOwners.Gulammustufa_S20_FE, new DeviceInfo("ARZCR909C4QFA", "Samsung S20 FE", "10"));
        DEVICE_INFO_MAP.put(DeviceOwners.Gulammustufa_S24_FE, new DeviceInfo("BRZCY20FJYAHB", "Samsung 24 FE", "15"));
        DEVICE_INFO_MAP.put(DeviceOwners.Dhairya, new DeviceInfo("CZD222FSTG7C", "Motorola", "14"));
        DEVICE_INFO_MAP.put(DeviceOwners.Nokia, new DeviceInfo("DPNXID19051303526D", "Nokia 8.1", "11"));
    }

    public static synchronized DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    public DeviceOwners acquireDevice() {
        refreshAvailableDevices();
        DeviceOwners device = availableDevices.poll();
        if (device != null) {
            inUseDevices.add(device);
        }
        return device;
    }

    public synchronized void releaseDevice(DeviceOwners deviceOwner) {
        if (deviceOwner != null && inUseDevices.contains(deviceOwner)) {
            inUseDevices.remove(deviceOwner);
            availableDevices.offer(deviceOwner);
        }
    }

    public synchronized void refreshAvailableDevices() {
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            Set<DeviceOwners> connectedDevices = new CopyOnWriteArraySet<>();

            while ((line = reader.readLine()) != null) {
                if (line.contains("device") && !line.contains("List of devices attached")) {
                    String serial = line.split("\t")[0].toUpperCase(); // Extract serial number
                    DeviceOwners owner = getOwnerBySerial(serial);

                    if (owner != null) {
                        connectedDevices.add(owner);
                    }
                }
            }

            // Add newly detected devices that are not already in use
            for (DeviceOwners owner : connectedDevices) {
                if (!availableDevices.contains(owner) && !inUseDevices.contains(owner)) {
                    availableDevices.offer(owner);
                }
            }

            // Remove devices that are no longer connected (but not in use)
            availableDevices.removeIf(owner -> !connectedDevices.contains(owner));

            testContext().getScenarioLogger().log("availableDevices = " + availableDevices);
        } catch (Exception e) {
            testContext().getScenarioLogger().log("Exception found while refreshing the devices. Error: " + e);
        }
    }

    public static DeviceOwners getOwnerBySerial(String udid) {
        for (Map.Entry<DeviceOwners, DeviceInfo> entry : DEVICE_INFO_MAP.entrySet()) {
            if (entry.getValue().udid().equalsIgnoreCase(udid)) {
                return entry.getKey();
            }
        }
        return null; // Serial not found
    }
}

