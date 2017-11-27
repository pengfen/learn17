<!doctype html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>html的文本标签</title>
	</head>
	<body>
		<p>
			p 标签定义段落
		</p>
		<p>
			p 标签定义段落
			<b>b 标签定义了文本中的部分比其余的部分更重要 并呈现为粗体</b>
			<strong>strong 标签定义重要的文本</strong>
		</p>
		
		<h1>h1-h6 标签可定义标题</h1>
		<h2>二标题</h2>
		<h3>三标题</h3>
		<h4>四标题</h4>
		<h5>五标题</h5>
		<h6>六标题</h6>
		<h7>七标题</h7>
		<sb>七公主</sb>
		
		<p>
			<em>em 标签呈现为被强调的文本</em>
			<i>i 标签呈现斜体的文本</i>
			<cite>cite 标签定义引用</cite>
			
			<sub>sub 标签可定义下标文本</sub>
			<sup>sup 标签可定义上标文本</sup>
		</p>
		
		
		<h2>文本格式标签</h2>
		<p>
			<bdo dir="rtl">bdo 标签覆盖默认的文本方向 属性 dir = ltr/rtl</bdo>

			<q>q 标签定义一个短的引用 使用cite 表示引用源</q>

			<del>del 标签定义文档中已删除的文本。</del>

			<abbr title="大屌丝">abbr 标签 表示一个缩写形式，比如 "Inc."、"etc."。通过对缩写词语进行标记，您就能够为浏览器、拼写检查程序、翻译系统以及搜索引擎分度器提供有用的信息。<abbr title="etcetera">etc.</abbr>
</abbr>
			<time>time 标签定义日期或时间，或者两者。属性：datetime='2012-12-12'</time>
			我跟小艳艳<time datetime='2015-07-07'>情人节</time>去约会
		</p>
		<!--
		<details open='true'>
			<summary>重要批示</summary>
			<p>小艳艳爱我</p>
			<p>小艳艳的妹妹爱我</p>
			<p>小艳艳的姐姐爱我</p>
		</details>
		-->
		
		<dialog>
			<dt>艳艳</dt>
			<dd>约吗</dd>
			<dt>我</dt>
			<dd>约</dd>
			<dt>艳艳</dt>
			<dd>哪里约</dd>
			<dt>我</dt>
			<dd>小树林</dd>
		</dialog>

		标签包含 details 元素的标题，"details" 元素用于描述有关文档或文档片段的详细信息。<details>
		<summary>Copyright 1999-2011.</summary>
		<p> - by Refsnes Data. All Rights Reserved.</p>
		<p>All content and graphics on this web site are the property of the company Refsnes Data.</p>
		details 标签定义元素的细节，用户可进行查看，或通过点击进行隐藏。

		</details>

		示对话<dialog><dt>角色</dt><dd>说话内容</dd></dialog>	
		
		<p>
			&lt;html&gt;
			
			&amp;
			&quot;
		</p>
		
		<p>
			我&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;艳艳
			
			&copy;
			&sect;
			&curren;
			&times;
		</p>
		
		
	</body>
</html>