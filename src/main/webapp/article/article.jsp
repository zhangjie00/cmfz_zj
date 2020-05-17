<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id',{
        uploadJson:"${path}/editor/uploadPhoto",  //指定文件上传的路径
        filePostName:"img",  //指定上传文件的名  默认imgFile
        allowFileManager:true,//显示浏览远程图片的按钮
        fileManagerJson:"${path}/editor/photos",  //指定浏览远程图片的路径
        afterBlur:function(){  //失去焦点之后执行的方法
            this.sync();   //将kindeditor中的内容同步到表单中
        }
    });
</script>
<script>
    $(function(){
        //创建表单
        $("#atcTable").jqGrid({
            url : "${path}/article/queryByPage",   //page  当前页    rows 每页展示条数
            editurl:"${path}/article/edit",
            datatype : "json",
            rowNum : 2,
            rowList : [2,5,10,20,30],
            pager : '#atcPage',
            viewrecords : true,  //是否展示总条数
            styleUI:"Bootstrap",
            height:"auto",
            autowidth:true,
            colNames : [ 'Id', '名称', '内容', '上师名', '发布时间'],
            colModel : [
                {name : 'id',index : 'id',width : 55},
                {name : 'title',index : 'id',width : 30},
                {name : 'content',index : 'id',width : 200,align:"center"},
                {name : 'guruName',editable:true,index : 'name asc, invdate',width : 50},
                {name : 'uploadTime',index : 'note',width : 50,sortable : false}
            ]
        });

        //增删改查操作
        $("#atcTable").jqGrid('navGrid', '#atcPage', {edit : false,add : false,del : true,search:false,addtext:"添加",edittext:"编辑"},
            {
                closeAfterEdit:true, //关闭添加框
            },   //修改之后的额外操作
            {
                closeAfterAdd:true, //关闭添加框
            },   //添加之后的额外操作
            {}    //删除之后的额外操作
        );


        //点击展示文章
        $("#btn1").click(function(){

            //先选中一行
            var rowId=$("#atcTable").jqGrid("getGridParam","selrow");
            //判断选中行的id
            if(rowId!=null){

                //根据行id返回指定行的数据
                var row =$("#atcTable").jqGrid("getRowData",rowId);

                //给标题的input设置内容
                $("#titles").val(row.title);
                //给作者的input设置内容
                $("#guruNames").val(row.guruName);
                //给kindeditor设置值
                KindEditor.html("#editor_id",row.content);

                //给模态框设置两个按钮
                $("#modalFooter").html("<button onclick='updateArticle(\""+rowId+"\")' class='btn btn-default'>保存</button >" +
                    "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>"
                );

                //展示模态框
                $("#articleModal").modal("show");

            }else{
                alert("请选中一行");
            }
        });

        //点击添加文章
        $("#btn2").click(function(){

            //重置表单
            $("#articleForm")[0].reset();

            //给kindeditor设置值
            KindEditor.html("#editor_id","");

            //展示模态框
            $("#articleModal").modal("show");

            //给模态框设置两个按钮
            $("#modalFooter").html("<button onclick='addArticle()' class='btn btn-default'>保存</button >" +
                "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>"
            );

        });

    });

    //点击添加文章按钮
    function addArticle(){
        $.ajax({
           url:"${path}/article/add",
           type:"post",
           dataType:"text",
           data:$("#articleForm").serialize(),
           success:function(){
               //关闭模态框
               $("#articleModal").modal("hide");
               //刷新表单
               $("#atcTable").trigger("reloadGrid");
           }
        });

    }

    //点击修改文章按钮
    function updateArticle(id){
        $.ajax({
            url:"${path}/article/update?id="+id,
            type:"post",
            datatype:"json",
            data:$("#articleForm").serialize(),
            success:function(){
                //关闭模态框
                $("#articleModal").modal("hide");
                //刷新表单
                $("#atcTable").trigger("reloadGrid");
            }
        });

    }

</script>

<%--初始化面板--%>
<div class="panel panel-danger">

    <%--面板标题--%>
    <div class="panel panel-heading">
        <h3>文章管理</h3>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a >文章管理</a></li>
    </ul>

    <div class="panel panel-body">
        <button id="btn1" class="btn btn-info">查看文章</button>&emsp;
        <button id="btn2" class="btn btn-success" >添加文章</button>&emsp;
        <button id="btn3" class="btn btn-warning">删除文章</button>
    </div>

    <%--初始化表单--%>
    <table id="atcTable"/>

    <%--分页工具栏--%>
    <div id="atcPage" />

</div>

<%--模态框--%>
<div id="articleModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:730px">

            <%--模态框标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">文章信息展示</h4>
            </div>

            <%--模态框的内容--%>
            <div class="modal-body">
                <%--放一个表单--%>
                <form class="form-horizontal" id="articleForm">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">标题</span>
                        <input id="titles" name="title" type="text" class="form-control"  aria-describedby="basic-addon1"/>
                    </div><br>


                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon3">作者</span>
                        <input id="guruNames" name="guruName" type="text" class="form-control" aria-describedby="basic-addon1"/>
                    </div><br>

                    <div class="input-group">
                        <%--引入输入框--%>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"/>
                    </div>

                </form>
            </div>

            <%--模态框按钮--%>
            <div class="modal-footer" id="modalFooter">
                <%--<button type="button" class="btn btn-default">保存</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
