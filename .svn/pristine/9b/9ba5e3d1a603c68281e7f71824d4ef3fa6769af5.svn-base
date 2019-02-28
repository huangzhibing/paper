<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>文件管理管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
        // 添加全局站点信息
        var BASE_URL = '/webuploader';
        var uploadUrl = '${ctx}/sys/file/webupload/upload?uploadPath=${uploadPath}';
        var delFileUrl ='${ctx}/sys/file/webupload/delete?id=';
        var fileNumLimit = '${fileNumLimit}';
        var fileSizeLimit = '${fileSizeLimit}';
        var type = '${type}';
        var allowedExtensions = '${allowedExtensions}';
        var mimeTypes = '${mimeTypes}';
        function init($list) {
                <%--var  urls = "${fileValues}".split("|");--%>
           	var  fileIds = "${fileIds}".split("|");
            var  urls = "${fileValues}".split("|");
            var sum = 0;
            for (var i=0; i<urls.length; i++){
                if (urls[i]!=""){
                    sum++ ;
                    $(".file-desc").remove();
                    $list.append('<div  class="row item list-item">'
                        +'<i class="fa fa-3x fa-file-pdf-o text-info col-sm-1 file-item"></i>'
                        +'<h5 class="info col-sm-5">'
                        +'<label style="width: 200px; margin-top: 6px;text-overflow:ellipsis; white-space:nowrap; overflow:hidden;">'+decodeURIComponent(urls[i].substring(urls[i].lastIndexOf("/")+1))+'</label>'
                        + '</h5>'
                        +' <p class="state col-sm-3  fa-success" style="margin-top: 15px">已上传</p>'
                        + '<div class="col-sm-3 ">'
                        + '<i class="fa fa-check-circle fa-2x file-item pull-right fa-success"></i>'
                        <c:if test="${ readonly == false }">
                        +  '<a class="fa fa-minus-circle remove-this fa-2x file-item pull-right fa-danger" data-id="'+fileIds[i]+'"></a>'
						</c:if>
                        +  '<a class="fa fa-cloud-download download-this fa-2x file-item pull-right text-info" data-url="'+urls[i]+'"></a>'
                        + '</div>'
                        + '</div>')


                }
            }

            return sum;
        }



        function getUploadFileValues() {
            var list = $("#jeeplus_file_list .list-item .download-this");
            var files = [];
            for(var i=0; i < list.length; i++){
                files.push($(list[i]).attr("data-url"));
			}
			return files.join("|");
        }

        function getUploadFileNames() {
            var list = $("#jeeplus_file_list .list-item label");
            var files = [];
            for(var i=0; i < list.length; i++){
                files.push($(list[i]).text());
            }
            return files.join(",");
        }

	</script>
	<link href="${ctxStatic}/plugin/webuploader-0.1.5/fileupload.css" rel="stylesheet" />
	<script src="${ctxStatic}/plugin/webuploader-0.1.5/fileupload.js"></script>
</head>
<body class="bg-white">
			<!--dom结构部分-->
			<div style="display: none">
				<input id="file001" type="file">
			</div>

			<div id="uploader" class="uploader">
				<c:if test="${ readonly == false }">
					<div class="btns">
						<div id="picker"><i class="fa fa-cloud-upload"></i> 添加文件</div>
							<%--<button id="ctlBtn" class="btn btn-default">开始上传</button>--%>
					</div>
				</c:if>

				<!--用来存放文件信息-->
				<div id="jeeplus_file_list" class="uploader-list">
					<div  class="file-desc">
						<p>上传文件到这里</p>
					</div>
				</div>

			</div>

</body>
</html>