# AndroidAppAutomationBaseFramework
This is base project for android app with appium.

To running the android app we need below software and packages.
1. Java
2. Node Js and Npm
3. Appium
4. Android Studio
    - It must include build tools, command line tools, platform tools and emulator.
    - Path must set for the ANDROID_HOME which is Android SDK path and for above tools also.
    - Add ANDROID_HOME in User variables section.
    - For tools, add it in System variables > Path.
    - Restart the system if you are facing the error related to path after adding the path.
    - 
5. Appium Inspector
   - It is not used for running the script. But it is used for locating the elements.
   - Go to appium server > advanced > Allow Corp: yes.
   - Start the appium server
   - Go to appium inspector.
   - Add below json data in the appium inspector.
     {
     "appium:platformName": "Android",
     "appium:deviceName": "Samsung Galaxy S20 FE 5G",
     "appium:udid": "AARZCR909C4QF",
     "appium:platformVersion": "12",
     "appium:appPackage": "com.abc.app.development",
     "appium:appActivity": "com.abc.app.MainActivity"
     }
   
   - Remote Host: localhost
   - Remote Port: 4723
   - Go to Advance Settings > Allow Unauthorised Certificates: Select the checkbox

   
After installing above things, start the Appium server, and then you can run the automation script.

For setting up the path and other details refer this url. /n
https://android.tutorials24x7.com/blog/how-to-install-android-sdk-tools-on-windows

Some important commands.
1. To see the available virtual devices
   - emulator -list-avds

2. To start the virtual devices
   - emulator -avd device-name

3. To install the app
   - adb install apk-file-name.apk

4. To see the available physical device
   - adb devices
