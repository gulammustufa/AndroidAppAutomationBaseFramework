package utility;

import utility.enums.DeviceOwners;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DeviceManager {
    private static final ConcurrentLinkedQueue<DeviceOwners> availableDevices = new ConcurrentLinkedQueue<>();
    private static DeviceManager instance;

    private DeviceManager() {
        availableDevices.add(DeviceOwners.Gulammustufa);
        availableDevices.add(DeviceOwners.Dhairya);
        availableDevices.add(DeviceOwners.Nokia);
    }

    public static synchronized DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    public DeviceOwners acquireDevice() {
        return availableDevices.poll();
    }

    public void releaseDevice(DeviceOwners deviceOwner) {
        availableDevices.offer(deviceOwner);
    }
}

