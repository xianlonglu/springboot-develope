<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
<body>
<h2>图样例</h2>
<image src="${(params.img)}"/>
<p>
3是否存在：${params.module3?if_exists};4是否存在：${params.module4?if_exists}
<p>
<#list params.list2 as x>
<p><image src="${(x)}"/>
</#list>
<div>
    <h2>表样例</h2>
    <table id="customers">
        <tr>
            <th>MessageCode</th>
            <th>MessageStatus</th>
            <th>Cause</th>
			<th>test</th>
        </tr>
        <tr>
            <td>${(params.messageCode)!""}</td>
            <td>${(params.messageStatus)!""}</td>
            <td>${(params.cause)!""}</td>
            <td>${(params.module)!"000"}</td>
        </tr>
    </table>
    <p><h2>表样例1</h2>
    <table id="customers1">
        <tr>
            <th>MessageCode1</th>
            <th>MessageStatus1</th>
            <th>Cause1</th>
			<th>test</th>
        </tr>
        <tr>
            <td>${(params.messageCode)!""}</td>
            <td>${(params.messageStatus)!""}</td>
            <td>${(params.cause)!""}</td>
            <td>${(params.module1)!"111"}</td>
        </tr>
    </table>
    <p><h2>表样例2</h2>
    <table id="customers2">
        <tr>
            <th>MessageCode2</th>
            <th>MessageStatus2</th>
            <th>Cause2</th>
			<th>test</th>
        </tr>
        <tr>
            <td>${(params.messageCode)!""}</td>
            <td>${(params.messageStatus)!""}</td>
            <td>${(params.cause)!""}</td>
            <td>${(params.module2)!"222"}</td>
        </tr>
    </table>
	<#if params.module3?exists>
    <p><h2>表样例3</h2>
    <table id="customers3">
        <tr>
            <th>MessageCode3</th>
            <th>MessageStatus3</th>
            <th>Cause3</th>
            <th>test</th>
        </tr>
        <tr>
            <td>${(params.messageCode)!""}</td>
            <td>${(params.messageStatus)!""}</td>
            <td>${(params.cause)!""}</td>
            <td>${(params.module3)!"333"}</td>
        </tr>
    </table>
	</#if>
	<#if params.module4?exists>
    <p><h2>表样例4</h2>
    <table id="customers4">
        <tr>
            <th>MessageCode4</th>
            <th>MessageStatus4</th>
            <th>Cause4</th>
			<th>test</th>
        </tr>
        <tr>
            <td>${(params.messageCode)!""}</td>
            <td>${(params.messageStatus)!""}</td>
            <td>${(params.cause)!""}</td>
            <td>${(params.module4)!"444"}</td>
        </tr>
    </table>
	</#if>
    <p><h2>表样例5</h2>
    <table id="customers5">
        <tr>
            <th>MessageCode5</th>
            <th>MessageStatus5</th>
            <th>Cause5</th>
			<th>test</th>
        </tr>
		<#list params.list1 as x>                  
		  <tr>
            <td>${(params.messageCode)!""}</td>
            <td>${x_index +1} ：${x} ： ${(params.messageStatus)!""}</td>
            <td>${(params.cause)!""}</td>
            <td>${(params.module5)!"555"}</td>
		  </tr>
		  <#-- 
		  ${x_index +1}.${x} <#if x_has_next>,</#if>
          <#if x = "星期四"><#break></#if>
		  -->
		</#list>        
    </table>
</div>
</body>
</html>