# TextCountDown
基于Chronometer实现的高度定制化的倒计时控件

## 使用
### 定义样式
|方法|作用|
|-----|-----|
|`init(_time_s)`|初始化计时时间，单位s|
|`setStarText`|设置计时之前的文本|
|`setEndText`|设置计时完成的文本|
|`setUnit`|设置计时中的数字后面的单位，例如‘秒’,如有特殊需求也可设置其他文本|
|`setPrefix`|设置计时中的数字前面的文本|
|`setFirstZero`|设置当计时数字首位为0时是否去掉0|
|`setTickBackgroundResource`|设置计时中的背景|
|`setFinishBackgroundResource`|设置计时结束的背景|
|`setTickBackgroundColor`|设置计时中的背景颜色|
|`setFinishBackgroundColor`|设置计时结束的背景颜色|
|`setFinishBackgroundColor`|设置计时结束的背景颜色|
|`setTickTextColor`|设置计时中的字体颜色|
|`setFinishTextColor`|设置计时结束后的字体颜色|
|`setTickClickable`|设置在计时中时是否可点击|
|`setTimeFormat`|设置计时数字格式，可选‘SECONDS’或‘MINUTES’|
|`setOnTimeListener`|设置计时中和计时完成后的事件|
|`setClickListener`|设置点击事件|
|`Bulid`|`样式配置完成后调用此方法初始化`|

### 使用
|方法|作用|
|----|-----|
|`reStart(long time)`|按照传入时间重新开始计时|
|`reStart`|重新开始计时|
|`onStart，onResume`|开始（恢复）计时|
|`onPause`|暂停计时|

#### 我在里面封装了两个常用的配置：
`buildSmsDefault`,
`buildSkipDefault`
可直接根据项目需求稍作更改，拿来即用














