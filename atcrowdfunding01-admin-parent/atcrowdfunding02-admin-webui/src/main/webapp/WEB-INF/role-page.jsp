<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-role.js"></script>
<script type="text/javascript">
    $(function(){
        // 1.为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";
        // 2.调用执行分页的函数，显示分页效果
        generatePage();

        $("#searchBtn").click(function (){
            window.keyword = $("#keywordInput").val();
            generatePage();
        });

        $("#showAddModelBtn").click(function (){
            $("#addModal").modal("show");
        });

        $("#saveRoleBtn").click(function (){
            var roleName = $.trim($("#addModal [name=roleName]").val());

            $.ajax({
                "url": "role/save.json",
                "type":"post",
                "data":{
                    "name":roleName
                },
                "dataType":"json",
                "success":function (response){
                    var result = response.result;
                    if (result == "SUCCESS"){
                        layer.msg("操作成功！");
                        // 将页码定位到最后一页
                        window.pageNum = 99999999;
                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED"){
                        layer.msg("操作失败！"+response.message);
                    }
                },
                "error":function (response){
                    layer.msg(response.status+" "+response.statusText);
                }
            });

            $("#addModal").modal("hide");

            // 清理模态框
            $("#addModal [name=roleName]").val("");
        });

        $("#rolePageBody").on("click",".pencilBtn",function(){
            $("#editModal").modal("show");
            var roleName = $(this).parent().prev().text();
            window.roleId = this.id;
            $("#editModal [name=roleName]").val(roleName);
        });

        $("#editRoleBtn").click(function(){

            // ①从文本框中获取新的角色名称
            var roleName = $("#editModal [name=roleName]").val();
            // ②发送 Ajax 请求执行更新
            $.ajax({
                "url":"role/update.json",
                "type":"post",
                "data":{
                    "id":window.roleId,
                    "name":roleName
                },
                "dataType":"json",
                "success":function(response){
                    var result = response.result;
                    if(result == "SUCCESS") {
                        layer.msg("操作成功！");
                        // 重新加载分页数据
                        generatePage();
                    }
                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }
                },
                "error":function(response){
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            // ③关闭模态框
            $("#editModal").modal("hide");
        });

        $("#removeRoleBtn").click(function (){
            var requestBody = JSON.stringify(window.roleIdArray);

            $.ajax({
                "url":"role/remove/by/role/id/array.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;chartset=UTF-8",
                "dataType":"json",
                "success":function(response){
                    var result = response.result;
                    if(result == "SUCCESS") {
                        layer.msg("操作成功！");
                        // 重新加载分页数据
                        generatePage();
                    }
                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }
                },
                "error":function(response){
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            $("#confirmModal").modal("hide");
        });

        $("#rolePageBody").on("click",".removeBtn",function(){

            var roleName = $(this).parent().prev().text();
            var roleArray = [{
                roleId:this.id,
                roleName:roleName
            }];
            showConfirmModel(roleArray);
        });

        $("#summaryBox").click(function (){

             var currentStatus = this.checked;
             $(".itemBox").prop("checked", currentStatus);
        });

        $("#rolePageBody").on("click",".itemBox",function(){

            var checkedBoxCount = $(".itemBox:checked").length;
            var totalBoxCount = $(".itemBox").length;
            $("#summaryBox").prop("checked", checkedBoxCount == totalBoxCount);
        });

        $("#batchRemoveBtn").click(function (){
            var roleArray = [];
            $(".itemBox:checked").each(function (){
               var roleId = this.id;
               var roleName = $(this).parent().next().text();
               roleArray.push({
                   "roleId":roleId,
                   "roleName":roleName
               });
            });
            if (roleArray.length == 0){
                layer.msg("请至少选择一个删除");
                return ;
            }
            showConfirmModel(roleArray);
        });

        $("#rolePageBody").on("click",".checkBtn",function(){
            window.roleId = this.id;
            $("#assignModal").modal("show");
            fillAuthTree();

        });

        $("#assignBtn").click(function(){
            // ①收集树形结构的各个节点中被勾选的节点
            // [1]声明一个专门的数组存放 id
            var authIdArray = [];
            // [2]获取 zTreeObj 对象
            var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
            // [3]获取全部被勾选的节点
            var checkedNodes = zTreeObj.getCheckedNodes();

            // [4]遍历 checkedNodes
            for(var i = 0; i < checkedNodes.length; i++) {
                var checkedNode = checkedNodes[i];
                var authId = checkedNode.id;
                authIdArray.push(authId);
            }
            // ②发送请求执行分配
            var requestBody = {
                "authIdArray":authIdArray, // 为了服务器端 handler 方法能够统一使用 List<Integer>方式接收数据，roleId 也存入数组
                "roleId":[window.roleId]
            };
            requestBody = JSON.stringify(requestBody);
            $.ajax({
                "url":"assign/do/role/assign/auth.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "success":function(response){
                    var result = response.result;
                    if(result == "SUCCESS") {
                        layer.msg("操作成功！");
                    }
                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }
                },
                "error":function(response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            $("#assignModal").modal("hide");
        });

    });

</script>

<body>

<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" id="batchRemoveBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" id="showAddModelBtn" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                                <tr>
                                    <th width="30">#</th>
                                    <th width="30"><input id="summaryBox" type="checkbox"></th>
                                    <th>名称</th>
                                    <th width="100">操作</th>
                                </tr>
                            </thead>
                            <tbody id="rolePageBody"></tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6" align="center">
                                        <div id="Pagination" class="pagination"></div>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/model-role-add.jsp"%>
<%@include file="/WEB-INF/model-role-edit.jsp"%>
<%@include file="/WEB-INF/model-role-confirm.jsp"%>
<%@include file="/WEB-INF/modal-role-assign-auth.jsp"%>
</body>
</html>
