<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>


    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <!-- 引入 ECharts 文件 -->
    <script src="${path}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${path}/js/goEasy.js"></script>
    <script type="text/javascript">

        $(function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            $.get("${path}/echarts/queryByUser",function(data){

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '用户月注册统计',  //标题
                        link:"${path}/main/main.jsp", //跳回主标题的超链接
                        subtext:"用户信息" //副标题
                    },
                    tooltip: {},  //鼠标提示
                    legend: {
                        data:['小男孩',"小女孩"]  //选项卡
                    },
                    xAxis: {  //横轴数据
                        data: data.month
                    },
                    yAxis: {},  //纵轴数据   自适应
                    series: [{
                        name: '小男孩',
                        type: 'bar',  //图标的类型
                        data: data.boys  //数据
                    },{
                        name: '小女孩',
                        type: 'line',  //图标的类型
                        data: data.girls  //数据
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },"json");


        });
    </script>
    <script type="text/javascript">

        /*初始化GoEasy对象*/
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-df0a45499f274b2bae29ae50a6a12dc9", //替换为您的应用appkey
        });

        $(function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            /*接收消息*/
            goEasy.subscribe({
                channel: "cmfz", //替换为您自己的channel
                onMessage: function (message) {
                    //alert("Channel:" + message.channel + " content:" + message.content);

                    //将json字符串转化为javascript对象
                    var data=JSON.parse(message.content);

                    console.log(data);

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '用户月注册统计',  //标题
                            link:"${path}/main/main.jsp", //主标题超链接
                            subtext:"用户信息" //副标题
                        },
                        tooltip: {},  //鼠标提示
                        legend: {
                            data:['小男孩',"小女孩"]  //选项卡
                        },
                        xAxis: {  //横轴数据
                            data: data.month
                        },
                        yAxis: {},  //纵轴数据   自适应
                        series: [{
                            name: '小男孩',
                            type: 'bar',  //图标的类型
                            data: data.boys  //数据
                        },{
                            name: '小女孩',
                            type: 'line',  //图标的类型
                            data: data.girls  //数据
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                }
            });
        });

    </script>
</head>
    <body>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div align="center">
            <div id="main" style="width: 600px;height:400px;" />
        </div>

    </body>
</html>