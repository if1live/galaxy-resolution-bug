# Galaxy Resolution Bug

갤럭시 시리즈에서 발생하는 해상도 버그를 기록했다.
버그 리포팅에 이용할 예정이다.

## 재현할 버그
* 패키지 이름이 `com.fiveminlab.toyclash` 일때 OS로부터 올바른 화면 해상도를 얻을 수 없다.
* 의도한 동작 : 패키지 이름에 상관없이 항상 올바른 해상도가 나와야한다.
* 테스트된 기기
    * Galaxy S6 egde (2017/05/17 당시 최신 OS)
    * Galaxy S8 (2017/05/17 당시 최신 OS)

## Timeline

### 패키지 이름에 따라서 해상도가 다르다?

유니티를 이용해서 기어VR 타겟으로 게임을 만들었는데 Galaxy S8에서 사시(crossed eyes)가 발생하는 현상이 발생했다.
갤럭시 S8을 빌려서 여러가지 테스트를 해본 결과 다음과 같은 결과를 얻었다.

갤럭시 S8에서 패키지 이름을 `com.fiveminlab.toyclash`로 하면 해상도가 1920x1080으로 나온다.
![갤럭시 S8, com.fiveminlab.toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s8_lower_1920_1080.png)

갤럭시 S8에서 패키지 이름을 `com.fiveminlab.Toyclash`로 하면 해상도가 2960x1440으로 나온다.
![갤럭시S8, com.fiveminlab.Toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s8_upper_2960_1440.png)

패키지 이름에 따라서 해상도가 다르다니?
뭔가 이상한 버그라서 갤럭시 S6에서도 테스트를 했다.

갤럭시 S6에서 패키지 이름을 `com.fiveminlab.toyclash`로 하면 해상도가 1280x720으로 나온다.
![갤럭시 S6, com.fiveminlab.toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s6_lower_1280_720.png)

갤럭시 S6에서 패키지 이름을 `com.fiveminlab.Toyclash`로 하면 해상도가 2560x1440으로 나온다.
![갤럭시 S6, com.fiveminlab.Toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/s6_upper_2560_1440.png)

갤럭시 S6에서도 있던 버그였다. S6에서는 1280/720=1.777 이고 2560/1440=1.777 로 해상도 비율이 같아서 티가 나지 않았다.   
2960/1440=2.0555 이고 1920/1080=1.777 로 해상도 비율이 달라서 S8에서는 문제가 발생했다.

조사한 자료를 모아서 유니티에 버그 리포팅을 했다

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

이슈는 등록되었고 답장이 올때까지 기다렸다.
그리고 답장을 받았다.

```
Hi!

The issue is not related to the package name beginning lowercase, rather with the specific case-sensitive package name. We tried other package names, lower and upper case - it just works.
Looks like the Samsung phone has something hardcoded for the specified package name, like the recommended resolution. There's nothing in Unity which makes it this way - we are utilizing the surface with the resolution as returned by the OS. Hence, there is no solution we can apply at the Unity side.

An obvious workaround is to use another package name. If we are talking about your released game, I'd recommend contacting Samsung for additional information.

I'm resolving it as "Won't Fix".
```

유니티 버그가 아니라 OS의 버그라고...?
패키지 이름을 바꾸든가 아니면 삼성한테 연락해서 문제를 해결하라고...?
그래서 샘플 프로젝트를 만들어서 다시 테스트해봤다.

### 샘플 프로젝트

기어VR에서만 생기는 버그인줄 알았는데 안드로이드에서 발생하는 버그였다.
그래서 유니티 프로젝트를 다시 만들었다.
`/unity`를 열어서 패키지 이름을 바꿔서 빌드를 해보면 다음의 결과를 확인할수 있다.

패키지 이름이 `com.fiveminlab.toyclash`일때 갤럭시 S6에서 해상도는 1280x720으로 나온다.
![com.fiveminlab.toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/unity_lower_com.fiveminlab.toyclash.png)

패키지 이름이 `com.fiveminlab.Toyclash`일때 갤럭시 S6에서 해상도는 2560x1440으로 나온다.
![com.fiveminlab.Toyclash](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/unity_upper_com.fiveminlab.Toyclash.png)

유니티에서 OS버그랬으니 순수 안드로이드 자바로도 버그를 발생시켰다. 안드로이드 자바 프로젝트는 `/android`에 있다.
유니티 QA에서 테스트해봤더니 첫번째 실행때는 정상적인 해상도가 나오고 두번째 실행부터는 해상도가 이상하게 나온다고 하더라.
그래서 똑같은 방식으로 테스트해봤다.

앱을 첫 실행하면 갤럭시 s6에서 해상도가 1440x2560으로 나온다.
![첫번째 실행](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/android-first-run.png)

앱을 완전히 종료한후(설정-어플리케이션-toyclash-강제 중지) 다시 실행하면 갤럭시 s6에서 해상도가 720x1280으로 나온다.
![두번째 실행](https://raw.githubusercontent.com/if1live/galaxy-resolution-bug/master/screenshots/android-second-run.png)

## APK
* apk-build/unity_lower_com.fiveminlab.toyclash.apk
    * 유니티, 패키지 이름은 `com.fiveminlab.toyclash`
* apk-build/unity_upper_com.fiveminlab.Toyclash.apk
    * 유니티, 패키지 이름은 `com.fiveminlab.Toyclash`
* apk-build/app-debug.apk, app-release-unsigned.apk
    * 자바 안드로이드, 패키지 이름은 `com.fiveminlab.toyclash`. 실행후 터치하면 해상도가 나온다.
