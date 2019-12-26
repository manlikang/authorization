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
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <LINK rel="Bookmark" href="/favicon.ico" >
        <LINK rel="Shortcut Icon" href="/favicon.ico" />
        <!--[if lt IE 9]>
        <script type="text/javascript" src="lib/html5.js"></script>
        <script type="text/javascript" src="lib/respond.min.js"></script>
        <script type="text/javascript" src="lib/PIE_IE678.js"></script>
        <![endif]-->
        <link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
        <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
        <!--[if IE 6]>
        <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
        <script>DD_belatedPNG.fix('*');</script>
        <![endif]-->
        <title>管理员列表</title>
        </head>
        <body id="userBody">
        <div class="pd-20">
        <table class="table table-border table-bordered table-bg">
        <%--展示一个菜单树--%>
        <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
        <%--授权新菜单的按钮--%>
        <input type="button" class="btn btn-primary radius" value="授权新菜单" id="resetRoleMenu">
        </div>
        </table>

        </div>
        <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
        <script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
        <script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="js/H-ui.js"></script>
        <script type="text/javascript" src="js/H-ui.admin.js"></script>
        <script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
        <script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.excheck-3.5.js"></script>

        <script type="text/javascript">

        var setting = {
        check: {
        enable: true
        },
        data: {
        key: {
        name: "meanName"
        },
        simpleData: {
        enable: true,
        idKey: "meanId",
        pIdKey: "parentId"
        }
        }
        };

        console.log(${menu});
        var zNodes =${menu};

        $.fn.zTree.init($("#treeDemo"), setting, zNodes);


            //授权新菜单
            $("#resetRoleMenu").click(function(){
            //得到所有选中节点的数组
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);

            var ids =  [];
            //得到所有选中的菜单节点的menuId
            for(var i=0;i<nodes.length;i++){
            //往数组放菜单id
            ids.push(nodes[i].meanId);
            }
            console.log(ids)
            //发送异步请求，把数据添加到数据库的表
            $.ajax({
            url:"sysMenu/AuthNewMenu",
            type:"GET",
            data:"ids="+ids+"&roleId="+${roleId},
            success:function(data){
            //{result:true}
            if(data.result){
            layer.msg('授权新菜单成功', {icon:6,time:2000});
            }else{
            layer.msg('授权新菜单失败', {icon:5,time:2000});
            }
            }
            })
            })
        </script>
        </body>
        </html>
