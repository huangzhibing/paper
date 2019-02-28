<%--
  Created by IntelliJ IDEA.
  User: M1ngz
  Date: 2018/9/24
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>专家地图</title>
    <script src="https://webapi.amap.com/loca?key=66faf1efcf7152452715ae97b9b94ce0"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <%--<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.10&key=66faf1efcf7152452715ae97b9b94ce0&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>--%>
    <style>
        .info {
            background: #363F49;
            color: #A0A7B4;
            padding: 10px;
            max-width: 300px;
            min-width: 200px;
            font-size: 12px;
        }

        .info tr .content {
            text-align: right;
            color: #D3D8E0;
            max-width: 200px;
        }

    </style>
</head>
<body>
    <div id='map' style='width:100%; height:100%;'></div>
    <script>
        var infoWin;
        var tableDom;
        /**
         * 封装便捷的撞题
         * @param {AMap.Map} map
         * @param {Array} event
         * @param {Object} content
         */
        function openInfoWin(map, event, content) {
            if (!infoWin) {
                infoWin = new AMap.InfoWindow({
                    isCustom: true,  //使用自定义窗体
                    offset: new AMap.Pixel(130, 100)
                });
            }

            var x = event.offsetX;
            var y = event.offsetY;
            var lngLat = map.containerToLngLat(new AMap.Pixel(x, y));

            if (!tableDom) {
                let infoDom = document.createElement('div');
                infoDom.className = 'info';
                tableDom = document.createElement('table');
                infoDom.appendChild(tableDom);
                infoWin.setContent(infoDom);
            }

            var trStr = '';
            for (var name in content) {
                var val = content[name];
                trStr +=
                    '<tr>' +
                    '<td class="label">' + name + '</td>' +
                    '<td>&nbsp;</td>' +
                    '<td class="content">' + val + '</td>' +
                    '</tr>'
            }

            tableDom.innerHTML = trStr;
            infoWin.open(map, lngLat);
        }

        function closeInfoWin() {
            if (infoWin) {
                infoWin.close();
            }
        }
    </script>
    <script>
        var map = new  Loca('map', {
            mapStyle: 'amap://styles/twilight',
            zoom: 5,
            viewMode: '3D',
            pitch: 50,
            center: [107.4976,32.1697]
        });
        var layer = Loca.visualLayer({
            eventSupport: true,    // 开启事件
            container: map,
            type: 'point',
            shape: 'prism',
            vertex: 4,
        });

        layer.on('mousemove', function (ev) {
            // 事件类型
            var type = ev.type;
            // 当前元素的原始数据
            var rawData = ev.rawData;
            // 原始鼠标事件
            var originalEvent = ev.originalEvent;

            openInfoWin(map.getMap(), originalEvent, {
                '名称': rawData.name,
                '专家数量': rawData.count
            });
        });
        layer.on('mouseleave', function (ev) {
            closeInfoWin();
        });
        $.get("${ctx}/university/university/mapData",function(res) {
            // console.log(res);
            var data = JSON.parse(res);
            // console.log(data)
            var displayData = [];
            for (var i = 0; i < data.length; i++) {
                data[i].height = data[i].count * 100
                displayData.push(data[i]);
            }
            layer.setData(displayData, {
                type: 'json',
                lnglat: 'center'
            });
            var topColors = [
                '#2c7bb6',
                '#abd9e9'
            ];
            layer.setOptions({
                unit: 'meter',
                style: {
                    // 正多边形顶面半径
                    topRadius: 20000,
                    // 正多边形顶面半径
                    bottomRadius: 50000,
                    height: {
                        key: 'count',
                        value: [80000, 300000]
                    },
                    color: topColors[0],
                    opacity: 0.9,
                    // 顶面颜色
                    topSideColor: topColors[1],
                    // 旋转角度，单位弧度
                    rotate: Math.PI / 180 * 45
                },
                selectStyle: {
                    radius: 14,
                    fill: '#fafffb'
                }
            });

            layer.render();
        })
/*        layer.setData(districts, {
            lnglat: 'center'
        });*/

    </script>
</body>
</html>
