# Galaxy Resolution Bug

It is a report about a bug which occurs in Galaxy S series.

## How to reproduce
* When an app has a package name as `com.fiveminlab.toyclash`, the app can't get the correct screen resolution.
* What should happen: no matter how the package name is, always the screen resolution must be correct.
* The test devices:
    * Galaxy S6 egde (the latest OS in May 17th 2017)
    * Galaxy S8 (the latest OS in May 17th 2017)


## Timeline

### Different package name, different resolution?

I made a game for Samsung Gear VR with Unity 3D engine.
Few days ago, I got a report of some customers had crossed-eyes problem who uses Galaxy S8
So I tested the game wiht Galaxy S8 and got results like below:

When the package name is `com.fiveminlab.toyclash`, the resolution is 1920x1080.
![갤럭시 S8, com.fiveminlab.toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s8_lower_1920_1080.png)

When the package name is `com.fiveminlab.Toyclash`, the resolution is 2960x1440.
![갤럭시S8, com.fiveminlab.Toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s8_upper_2960_1440.png)

It is really strange so I tested it with Galaxy S6

With Galaxy S6 and when the package name is `com.fiveminlab.toyclash` , the resolution is 1280x720.
![갤럭시 S6, com.fiveminlab.toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s6_lower_1280_720.png)


With Galaxy S6 and when the package name is `com.fiveminlab.Toyclash` , the resolution is 2560x1440.
![갤럭시 S6, com.fiveminlab.Toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s6_upper_2560_1440.png)

It shows the bug has been occuring even in Galaxy S6 but just Galaxy S6 has same screen ratio (1280/720 = 1.777 and 2560/1440 = 1.777) so customers just can't recognize the problem.

And I report to Unity with a summary of above bug investigating.

### Unity Issue Tracker
[Issue](https://issuetracker.unity3d.com/issues/android-returns-wrong-resolution-on-second-launch-if-productname-name-in-the-package-name-starts-with-lowercase)

**[ANDROID] RETURNS WRONG RESOLUTION ON SECOND LAUNCH IF PRODUCTNAME IN THE PACKAGE NAME STARTS WITH LOWERCASE**

```
Steps to reproduce:

1) Download attached project 'bug-resolution-gearvr.zip' and open in Unity 
2) In Player Settings, disable Virtual Reality Supported 
3) In Player Settings, make sure ProductName in the Package Name starts with lowercase at the beginning (com.fiveminlab.toyclash) 
4) Build and Run project on a device 
5) After first launch, close the application and reopen it 
Resolution will change from 2560x1440 to 1920x1080(Image attached)

Expected result: Resolution should stay at 2560x1440 
Actual result: Screen resolution returns to 1920x1080 when launching application second time

Reproduced with: 
5.5.1f1, 5.5.3p3, 5.6.1f1, 2017.1.0b5

DUT: 
Samsung SM G925F (Galaxy S6 Edge)*, OS:6.0.1, CPU:arm64-v8a, GPU:Mali-T760, Build:samsung/zeroltexx/zerolte:6.0.1/MMB29K/G925FXXU5DQA7:user/release-keys 
Samsung SM G928F*, OS:7.0, CPU:arm64-v8a, GPU:Mali-T760, Build:samsung/zenltexx/zenlte:6.0.1/MMB29K/G928FXXS3BPIA:user/release-keys

Note: Resolution is always at 2560x1440 if ProductName name in the Package Name starts with uppercase (com.fiveminlab.Toyclash)

Note: Reproduced only with this package name 'com.fiveminlab.toyclash' . Change the PackageName to something other 
e.g. 'com.hey.hello', and resolution will be returned correctly on every launch
```

An issue is made and I waited for the response.
And here is the response:

```
Hi!

The issue is not related to the package name beginning lowercase, rather with the specific case-sensitive package name. We tried other package names, lower and upper case - it just works.
Looks like the Samsung phone has something hardcoded for the specified package name, like the recommended resolution. There's nothing in Unity which makes it this way - we are utilizing the surface with the resolution as returned by the OS. Hence, there is no solution we can apply at the Unity side.

An obvious workaround is to use another package name. If we are talking about your released game, I'd recommend contacting Samsung for additional information.

I'm resolving it as "Won't Fix".
```

It says it is not the bug from Unity and it is bug from OS so that I have to change the package name or contact to Samsung.
To verify it again, I made another sample project

### The sample project
First of all, I made an simple Unity project. 
I just changed the package name with opening `/unity` folder.

When the package name is `com.fiveminlab.toyclash`, Galaxy S6 shows the resolution as 1280x720.
![com.fiveminlab.toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/unity_lower_com.fiveminlab.toyclash.png)

When the package name is `com.fiveminlab.Toyclash`, Galaxy S6 shows the resolution as 2560x1440.
![com.fiveminlab.Toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/unity_upper_com.fiveminlab.Toyclash.png)

The Unity issue guy said it is just OS's bug so I made a Java project. The project is located in `/android`.
Unity QA team said at the first launching, the screen resolution was correct and from the second launching, the screen resolution wasn't correct.
So I tested it in same way.

When I launched the test app at the first, the app shown the resolution as 1440x2560.
![첫번째 실행](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/android-first-run.png)

After I killed the app (Setting App -> Application -> ToyClash -> Shutdown) and I launched it again, the app shown the resolution as 720x1280.
![두번째 실행](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/android-second-run.png)

## APK
* apk-build/unity_lower_com.fiveminlab.toyclash.apk
    * made with Unity, the package name is `com.fiveminlab.toyclash`
* apk-build/unitgy_upper_com.fiveminlab.Toyclash.apk
    * made with Unity, the package name is `com.fiveminlab.Toyclash`
* apk-build/app-debug.apk, app-release-unsigned.apk
    * made with Java, the package name is `com.fiveminlab.toyclash`. To touch the screen after the app is loaded, the app shows the resolution.
