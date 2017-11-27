<!doctype html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>HTML</title>
	</head>
	<body>
		<h1>多媒体标签 </h1>
		<hr>
		<h3>图片 img(image)标签定义图像。src,width,height,alt,ismap,usemap
</h3>
		<img src="../images/bg01.jpg">
		<img src="../images/bg01.jpg" width="100">
		<img src="../images/bg01.jpg" width="100" height='100'>
		<img src="../images/bg01.jpg" border='1'>
		<img src="../images/bg01.jpg" border='10'>
		<img src="../images/bg01.jpg" border='10' alt="这是一张图片">
		<img src="../images/bg01.jpg" border='10' alt="这是一张图片" title="这是一张图片" />
		
		<hr>
		<hr>
		<h3>map 标签定义客户端的图像映射。图像映射是带有可点击区域的图像。id</h3>
		<h3>标签定义图像映射中的区域。alt,shape,target,href,coords</h3>
		<img src="../images/logo.png" usemap="#yanyan">
		<map  name="yanyan">
			<area shape="rect" coords="0,0,130,38" href="http://www.baidu.com" target="_blank"/>
			<area shape="rect" coords="0,39,130,78" href="http://www.lampbrother.net" target="_blank"/>
		</map>
		<hr>
		<img src="../images/bg.jpg" usemap="#xiaoyanyan" width=600>
		<map name="xiaoyanyan">
			<area shape="rect" coords="0,0,100,100" href="http://www.sina.com" target="_blank"/>
			<area shape="circle" coords="110,50,50" href="http://www.qq.com" target="_blank"/>
			<area shape="poly" coords="100,100,450,400,450,500" href="http://www.163.com" target="_blank"/>
		</map>
		
		<h3>audio 标签定义声音autoplay,controls,preload,src</h3>
		<audio src="../video/music.mp3" controls></audio>
		<!--
		<audio src="../video/music.mp3" controls autoplay>您的破浏览器不支持音乐</audio>
		-->
		
		<h3>video 标签定义视频autoplay,controls,height,width,loop,preload,src,poster</h3>
		<video src="../video/croods-01.mp4" controls width=300 poster="../images/bg02.jpg">
			您的破浏览器该换了
		</video>
		
		<hr>
		
		<h3>source 定义媒介资源 media src type</h3>
		<video controls width=600>
			<source src="../video/movie.ogg">
			<source src="../video/movie.webm">
			<source src="../video/croods-01.mp4">
		</video>

		<h3>canvas 标签定义图形</h3>
		
		<h3>embed 标签定义嵌入的内容，比如插件width,height,src,type</h3>
		<hr>
		<embed src="../video/music.mp3" width=600 height=40></embed>
		<embed src="../video/croods-01.mp4" width=600 ></embed>
		
		<hr>
		<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="900" height="300" id="FlashZhuan" >
			<param name="movie" value="../video/flash.swf">
			<param name="FlashVars" value="prizeResult=3">
			<param name="quality" value="high">
			<param name="menu" value="false">
			<param name="wmode" value="transparent">
			<param name="allowScriptAccess" value="always" />            
			<embed src="../video/flash.swf" FlashVars="prizeResult=3" allowScriptAccess="always" wmode="transparent" menu="false" quality="high" width="900" height="700" type="application/x-shockwave-flash" pluginspage="http://get.adobe.com/cn/flashplayer/" name="FlashZhuan"/>
		</object>
	</body>
</html>