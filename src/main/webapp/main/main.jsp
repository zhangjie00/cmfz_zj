<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>

    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- 导航栏标题 -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
            </div>

            <!-- 导航栏内容 -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎:<span class="text text-primary">${admin.username}</span></a></li>
                    <li class="dropdown">
                        <a href="#">退出登录<span class="glyphicon glyphicon-log-out"></span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!--栅格系统-->
    <div class="container-fluid">
        <div class="row">
            <!--左边手风琴部分-->
            <div class="col-md-2">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" align="center">


                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    轮播图管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class="btn btn-info">
                                            <a href="javascript:$('#mainId').load('${path}/banner/banner-show.jsp')">轮播图展示</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class="btn btn-warning">
                                            <a href="javascript:$('#mainId').load('${path}/user/user.jsp')">用户展示</a>
                                        </button>
                                    </li><br>
                                    <li>
                                        <button class="btn btn-success">
                                            <a href="javascript:$('#mainId').load('${path}/test/testEchartsGoEasy.jsp')">用户统计</a>
                                        </button>
                                    </li><br>
                                    <li><button class="btn btn-success">用户分布</button></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="panel panel-warning">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    专辑管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class="btn btn-warning">
                                            <a href="javascript:$('#mainId').load('${path}/album/album.jsp')">专辑展示</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="panel panel-danger">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    文章管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                    <button class="btn btn-danger">
                                        <a href="javascript:$('#mainId').load('${path}/article/article.jsp')">文章展示</a>
                                    </button>
                                    </li>
                                </ul><br/>

                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class="btn btn-danger">
                                            <a href="javascript:$('#mainId').load('${path}/article/articleSearch.jsp')" >搜索文章</a>
                                        </button>
                                    </li>
                                </ul><br/>





                            </div>
                        </div>
                    </div>

                    <hr>

                    <div class="panel panel-primary">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    上师管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li><button class="btn btn-primary">上师展示</button></li>
                                </ul>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

            <!--右边内容部分-->
            <div class="col-md-10" id="mainId">
                <!--巨幕开始-->
                <div class="jumbotron">
                    <h2>欢迎来到持明法洲后台管理系统</h2>
                </div>
                <!--右边轮播图部分-->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" align="center">
                    <!-- 小角标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- 图片链接 -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="${path}/bootstrap/img/shouye.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/bootstrap/img/1.png" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/bootstrap/img/2.png" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                    </div>

                    <!-- 前一张后一张按钮 -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

                <!--页脚-->
                <div class="panel panel-footer" align="center">
                    <div>@百知教育 zhangcn@zparkhr.com </div>
                </div>

            </div>
        </div>
    </div>




    
    <!--栅格系统-->

</body>
</html>
