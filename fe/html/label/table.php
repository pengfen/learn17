<!doctype html>
<html>
	<head>
		<meta charset='utf-8'>
		<title>HTML</title>
	</head>
	<body>
		<h1>table 标签可定义表格 </h1>
		<hr>
		<table width=600 border=1 cellspacing=0 cellpadding=5 height=400 bgcolor="#eee" bordercolor="red" >
			<caption>caption 标签定义表格标题</caption>
			<thead bgcolor="#ccc" align="left">
				<tr height="60">
					<th>编号</th>
					<th>姓名</th>
					<th>年龄</th>
					<th>性别</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<td colspan='4'>th 标签定义表格内的表头单元格 colspan rowspan scope</td>
				</tr>
			</tfoot>
			<tbody align='center'>
				<tr bgcolor="green" background="" height=58 align="right" valign="middle">
					<td width=100 bgcolor="blue" align="center" valign="bottom">1</td>
					<td rowspan=2>tr 标签定义表格中的行</td>
					<td>td 标签定义表格中的一个单元格</td>
					<td>thead 定义表格的表头</td>
				</tr>
				<tr>
					<td>thoot 定义表格的页脚 (脚注)</td>
					
					<td>17</td>
					<td>女</td>
				</tr>
				<tr>
					<td>col 标签为表格中的一个或多个列定义属性值 只能在表格或列组中使用该元素 属性span</td>
					<td>colgroup 标签定义表格列的组 通过此标签 您可以对列进行组合 以便格式化 colgroup 元素只能包含 col 元素 属性 span</td>
					<td>86</td>
					<td>女</td>
				</tr>
				<tr>
					<td>1</td>
					<td>小艳艳</td>
					<td>4</td>
					<td>女</td>
				</tr>
			</tbody>
		</table>
		
		<hr>
		
		<table width=800 border=1 cellspacing=5 cellpadding=10 bordercolor="#ccc">
			<tr bgcolor="#ccc">
				<th>序号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>班级</th>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr><tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
		</table>
		
		
		<table width=800 border=1 cellspacing=5 cellpadding=10 bordercolor="#ccc">
			<colgroup span="1" style="background:red">
				<col span='1' style="background:green">
				<col span='2' style="background:yellow">
				<col span='1' style="background:purple">
			</colgroup>
			<tr bgcolor="#ccc">
				<th>序号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>班级</th>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr><tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
			<tr>
				<td>1</td>
				<td>翠翠</td>
				<td>16</td>
				<td>s24</td>
			</tr>
		</table>
		
		
		<hr>
		
		
		<table border=1 width=600 cellspacing=0>
			<caption><h3>简 历</h3></caption>
			<tr height=50>
				<th width='100'>姓 名</th>
				<td width='100'></td>
				<th width='100'>性 别</th>
				<td width='100'></td>
				<th width='100'>出 生</th>
				<td width='100'></td>
			</tr>
			<tr height=50>
				<th width='100'>姓 名</th>
				<td width='100'></td>
				<th width='100'>性 别</th>
				<td width='100'></td>
				<th width='100'>出 生</th>
				<td width='100'></td>
			</tr>
			<tr height=50>
				<th width='100'>健康状况</th>
				<td width='100'></td>
				<th width='100'>毕业院校</th>
				<td width='100'></td>
				<th width='100'>出 生</th>
				<td width='100'></td>
			</tr>
			<tr height='120'>
				<th width="100">教 育<br><br>经 历</th>
				<td colspan='5'></td>
			</tr>
			<tr height='120'>
				<th width="100">教 育<br><br>经 历</th>
				<td colspan='5'></td>
			</tr>
			<tr height='120'>
				<th width="100">教 育<br><br>经 历</th>
				<td colspan='5'></td>
			</tr>
			<tr height='160'>
				<th width="100">联 系<br><br>方 法</th>
				<td colspan='5'>电话：<br><br>手机：<br><br>地址：<br><br>邮编：</td>
			</tr>
		</table>
		
	</body>
</html>