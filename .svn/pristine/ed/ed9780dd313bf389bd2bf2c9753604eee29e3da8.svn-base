<%--
  Created by IntelliJ IDEA.
  User: M1ngz
  Date: 2018/9/24
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>地图选点</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=66faf1efcf7152452715ae97b9b94ce0&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
    <script>
        window.onload = function() {
            map.plugin(["AMap.ToolBar"], function() {
                map.addControl(new AMap.ToolBar());
            });
            if(location.href.indexOf('&guide=1')!==-1){
                map.setStatus({scrollWheel:false})
            }
        }
    </script>
    <style>
        .button-group {
            position: absolute;
            bottom: 20px;
            right: 20px;
            font-size: 12px;
            padding: 10px;
        }

        .button-group .button {
            height: 28px;
            line-height: 28px;
            background-color: #0D9BF2;
            color: #FFF;
            border: 0;
            outline: none;
            padding-left: 5px;
            padding-right: 5px;
            border-radius: 3px;
            margin-bottom: 4px;
            cursor: pointer;
        }
        .button-group .inputtext {
            height: 26px;
            line-height: 26px;
            border: 1px;
            outline: none;
            padding-left: 5px;
            padding-right: 5px;
            border-radius: 3px;
            margin-bottom: 4px;
            cursor: pointer;
        }
        /*
       .tip {
           position: absolute;
           bottom: 30px;
           right: 10px;
           background-color: #FFF;
           text-align: center;
           border: 1px solid #ccc;
           line-height: 30px;
           border-radius: 3px;
           padding: 0 5px;
           font-size: 12px;
       }
       */
        #tip {
            background-color: #fff;
            padding-left: 10px;
            padding-right: 10px;
            position: absolute;
            font-size: 12px;
            right: 10px;
            top: 20px;
            border-radius: 3px;
            border: 1px solid #ccc;
            line-height: 30px;
        }

        /*
        #tip input[type='button'] {
            margin-top: 10px;
            margin-bottom: 10px;
            background-color: #0D9BF2;
            height: 30px;
            text-align: center;
            line-height: 30px;
            color: #fff;
            font-size: 12px;
            border-radius: 3px;
            outline: none;
            border: 0;
        }
        */
        .amap-info-content {
            font-size: 12px;
        }

        #myPageTop {
            position: absolute;
            top: 5px;
            right: 10px;
            background: #fff none repeat scroll 0 0;
            border: 1px solid #ccc;
            margin: 10px auto;
            padding:6px;
            font-family: "Microsoft Yahei", "微软雅黑", "Pinghei";
            font-size: 14px;
        }
        #myPageTop label {
            margin: 0 20px 0 0;
            color: #666666;
            font-weight: normal;
        }
        #myPageTop input {
            width: 170px;
        }
        #myPageTop .column2{
            padding-left: 25px;
        }
        #container {
            width: 100%;
            height: 96%;
        }
    </style>
</head>
<body>
    <div id="container"></div>
    <div id="textSpan"></div>
    <div id="myPageTop">
        <table>
            <tr>
                <td>
                    <label>请输入关键字：</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input id="tipinput"/>
                </td>
            </tr>
        </table>
    </div>
    <input hidden id="map-gxjd" />
    <input hidden id="map-gxwd" />
    <script>
        var map = new AMap.Map('container');
        var autoOptions = {
            input: "tipinput"
        };
        var auto = new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map
        });  //构造地点查询类
        AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
        function select(e) {
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name,function(status,result) {
                console.log(result)
            });  //关键字查询查询
            placeSearch.on("markerClick",function(event){
                console.log(event)
                $("#textSpan").empty();
                $("#textSpan").append("<span>您当前选中点的坐标为：" + event.data.location.lng + " , " + event.data.location.lat +"</span>");
                $("#map-gxwd").val(event.data.location.lat)
                $("#map-gxjd").val(event.data.location.lng)
            })
        }
        if($('gxmc').val()) {
            placeSearch.search($('#gxmc').val());
        }
        function getJD() {
            return $("#map-gxjd").val()
        }
        function getWD() {
            return $("#map-gxwd").val()
        }
    </script>
</body>
</html>
