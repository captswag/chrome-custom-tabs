# Chrome Custom Tabs

**[Tutorial on my blog](http://anjithsasindran.in/blog/2015/09/28/chrome-custom-tabs/)**

Sample code, which demonstrates the usage of Chrome Custom Tabs with action buttons,
menu items and compares it with opening a webpage in Chrome Browser and WebView. 

### What is Chrome Custom Tabs

Chrome Custom Tabs is a new way to show third party content in android, which
allows you to change how chrome looks and feels making the transition from app
to web seemless.

With Chrome Custom Tabs, you can have

- Toolbar color
- Start/Exit animation
- Up button
- Action buttons
- Menu items

![Sample preview of Chrome Custom Tabs](/screenshot/preview.png)

### Usage

1. Include the gradle dependency
2. Connecting to browser service
3. Chrome warmup
4. Prefetch the webpage
5. Load the webpage in Chrome Custom Tabs

### Keep In Mind

Chrome Custom Tabs is only supported in Chrome browsers which are version 45 & above. 

Also google has forgot to add dependency name in their [sample codes on GitHub]
(https://github.com/GoogleChrome/custom-tabs-client).
The gradle dependency for Chrome Custom Tabs is
```
compile 'com.android.support:customtabs:23.0.0'
```

#### Author
**Anjith Sasindran**
- https://twitter.com/anjithsasindran
- https://instagram.com/anjithsasindran/
- https://github.com/4k3R
