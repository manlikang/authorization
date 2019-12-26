
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/26
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
    <script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<%--    <script type="text/javascript" src="js/H-ui.js"></script>--%>
<%--    <script type="text/javascript" src="js/H-ui.admin.js"></script>--%>
    <script type="text/javascript">
        $(function () {
            $("#select1").click(function () {
                console.log("我进来了");
                var userName = $("#userName").val();
                // console.log(userName);
                console.log('${roleId}');
                $("#userBody").load("sysRole/getNoAuthUser", {'userName': userName,'roleId':'${roleId}'});
            });
            $("#batchAuth").click(function () {
                var ids = [];
                $(".checkboxId:checked").each(function(index){
                    var id = $(this).val();
                    ids.push(id);
                });


                layer.confirm('确认要给'+ids+'授权吗？',function(index){
                    //此处请求后台程序，下方是成功后的前台处理……
                   $.ajax({
                       url:"sysRole/bathAuth",
                       type:'GET',
                       data:"ids="+ids+"&roleId=${roleId}",
                       success:function (data) {
                           if(data.result){
                               layer.msg('已授权!',{icon:6,time:1000});
                               location.reload();
                           }else{
                               layer.msg('授权失败!',{icon:5,time:1000});
                               location.reload();
                           }
                       }
                   })

                });
            })

            var $input = $(".checkboxId");
            var count =0;
            $("#all-checkbox").on("click",function () {
                if($(this).attr("checked") === 'checked') {
                    $(".checkboxId").attr('checked', 'checked');
                    count = $input.length
                }else{
                    $(".checkboxId").removeAttr("checked");
                    count = 0;
                }
            });
            $(".checkboxId").on("click",function () {
                if($(this).attr("checked") ==="checked"){
                    count++
                }else{
                    count--;
                }
                console.log(count,$input.length );
                if(count===$input.length){
                    console.log("全选了");
                    $("#all-checkbox").attr('checked', 'checked');
                }else{
                    $("#all-checkbox").removeAttr("checked")
                }


            });
        });
    </script>
    <title>Title</title>
</head>
<body id="userBody">
<div class="pd-20">
    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-3">
                请搜索:<input type="text" class="input-text" placeholder="输入用户名进行搜索" id="userName" value="${userName}" style="width: 250px">
            </div>
        </div>
        <div class="row cl">
            <div class="cl pd-5" >
                <button type="button"
                        style="left: 20px;" class="btn btn-success radius" id="select1">
                    <i class="Hui-iconfont">&#xe665;</i> 搜用户
                </button>
            </div>
        </div>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" id="batchAuth" class="btn btn-primary radius">
                <i class="Hui-iconfont">&#xe600;</i> 批量授权</a></span>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr class="text-c">
            <th width="25"><input type="checkbox" id="all-checkbox" name="" value=""></th>
            <th width="40">ID</th>
            <th width="150">用户名</th>
            <th width="90">电话</th>
            <th width="150">邮箱</th>
            <th>生日</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="user">
            <tr class="text-c">
                <td><input type="checkbox" class="checkboxId" value="${user.userId}" name=""></td>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
                <td class="td-manage"><a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin-add.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <jsp:include page="/page.jsp">
        <jsp:param name="bodyId" value="userBody"></jsp:param>
    </jsp:include>
</div>



<script type="text/javascript">



    /*
        参数解释：
        title	标题
        url		请求的url
        id		需要操作的数据id
        w		弹出层宽度（缺省调默认值）
        h		弹出层高度（缺省调默认值）
    */
    /*管理员-增加*/

    /*管理员-删除*/
    function admin_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……

            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }
    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-停用*/
    function admin_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……

            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
            $(obj).remove();
            layer.msg('已停用!',{icon: 5,time:1000});
        });
    }

    /*管理员-启用*/
    function admin_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……


            $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
            $(obj).remove();
            layer.msg('已启用!', {icon: 6,time:1000});
        });
    }
</script>
</body>
</html>
