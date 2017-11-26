# 开发文档

## 更新日志
### 2017/11/16 23:30 by光南
- 初步设计了程序逻辑，详见思维导图，不过突然发现忘记设计约运动的部分了，下周接着设计
- 部署了服务器、数据库
- 实现了微信自动登陆，大家可以[登录](http://sysustudentunion.cn/shallwe/login.html)试一下
- 写了后面的【开发工具】、【文件结构】、【项目推进计划】
- 妈呀一整个周末就搞了这些，累死爸爸了

## 开发工具
- 写在前面
	- 建议按以下顺序安装
	- 强烈建议所有安装都在默认路径，不然容易被坑死
	- 安装教程是我百度后筛选出来的，不一定按照它的就能成功安装，但是根据我的经验这些教程比较详细且具有参考价值
- java
	- 想写java程序必须装这个环境
	- [参考教程](https://jingyan.baidu.com/article/bea41d43bef8fab4c41be67b.html)
	- 注意环境变量配置是否正确
- tomcat8
	- 这是网页服务器引擎
	- 装tomcat8不要装tomcat9
	- 不要下载解压安装包，下载程序[安装包](http://mirrors.hust.edu.cn/apache/tomcat/tomcat-8/v8.5.23/bin/apache-tomcat-8.5.23.exe)
	- [参考教程](https://jingyan.baidu.com/article/6b97984db791911ca2b0bfc4.html)
	- 教程里面没写配置环境变量，可能需要配置，自己搜索教程配置吧
	- 把端口改成80，见[教程](https://jingyan.baidu.com/article/9113f81b22d1802b3214c7c6.html)
- Eclipse Java EE IDE for Web Developers.
	- 这是IDE
	- 我用的是Mars2
	- 在eclipse中配置tomcat8运行环境的[教程](http://blog.csdn.net/shirenfeigui/article/details/7699996)**教程里是tomcat7.0，操作时注意选择tomcat8.0即可**
	- 大家都用这个就可以直接把工程导入了，File->Import->General->Existing Projects into Workspace(应该是这个，不是的话就试一下File system)
- Mysql（可选）
	- 这是数据库
	- 建议安装5.7版本，注意要装[社区版](https://dev.mysql.com/downloads/windows/installer/5.7.html)，不然收费
	- 教程略
	- 如果使用服务器上的数据库就不用装了
		- 域名：	sysustudentunion.cn
		- 用户名：	dbteam
		- 密码：	666666
		- 这个账号只有权限操作两个数据库
			- `test`这个是给大家来练手，随便玩的
			- `shallwe`这个是给设计数据库的同学用的
- Navicat
	- 用于连接数据库的可视化软件，特别方便，建议使用
	- 下载[链接](http://pan.baidu.com/s/1nvj5gsp)

- Github
	- 使用GitHub实现代码同步与管理
	- 建议使用GitHub桌面版
	- 大家记得把GitHub账号发给我，我把大家拉进工作组
	- 建议每次使用GitHub之前都把服务器上的更新拉取下来
	- 大家每次上传前记得写一下readme.md的更新日志哟（使用Markdown）

## 在eclipse中打开后的文件结构
- `Deployment Descriptor/`不用管
- `JAX-WS Web Service/`不用管
- `Java Resources`
	- `src/`**放大家写的类的地方**
		- `pac/`这是目前的默认包，大家可以把写的类放这里（在这个文件夹下右键新建类）
			- `User.java`用户类，在用户登录时初始化，获取用户信息
			- `ErrorRecord.java`用于记录运行时的错误日志，用于Debug
			- 写的其他类也放在这
		- 如果有需要可以在这里建别的包
	- `Libraries/`通过Build Path导入的包会在这里面看到
- `JavaScript Resources`不用管
- `build/`eclipse自动生成的java编译文件，不用管
- `WebContent/`**放大家写的jsp网页的地方**
	- `image`用于放图片的地方，引用方法参照login.html
	- `META-INF`不用管
	- `WEB-INF`**大家如果需要用到其他第三方包，在资源管理器中把.jar包放进这里来，然后再buildpath**
	- login.html用户入口
	- stadium.jsp场馆选择
	- 其他页面名称参考**思维导图**
	- **大家记得把jsp文件都直接放在这里呀，不要放到子文件夹里面去了**

## 项目推进计划
### 第十三周11.27 0:00 - 12.3 23:59
- 在自己电脑上安装上述开发工具
- 除光南外每人实现一个用户登录和注册的小网页来练练手
	- 新建一个`Dynamic Web Project`项目
	- 请熟悉`javabean`的使用
	- 使用`jdbc`方法连接`test`数据库
	- 做完了之后在项目上右键->export->WAR file 导出发给光南挂在服务器上，就可以通过外网访问，看是否成功
- 光南会把所有接口都设计好
- 项目将改成两名前端，一名后端，大家可以报名了

### 第十四周12.4 0:00 - 12.10 23:59
- 后端同学完成一半后端实现（具体是哪部分等下次更新），并通完成测试

### 第十五周 12.11 0:00 - 12.17 23:59
- 前端同学依据后端同学上周实现的那一半接口，设计网页
- 后端同学完成另一半后端实现，并通过测试

### 第十六周 12.18 0:00 - 12.24 23:59
- 前端同学依据后端同学上周实现的那一半接口，设计网页