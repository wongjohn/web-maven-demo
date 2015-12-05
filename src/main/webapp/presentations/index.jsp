<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">

    <title>爱乐游官网堆栈</title>

    <meta name="description" content="爱乐游官网堆栈">
    <meta name="author" content="Wong John">

    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <link rel="stylesheet" href="css/reveal.min.css">
    <link rel="stylesheet" href="css/theme/default.css" id="theme">
    <link rel="stylesheet" href="css/architecture.css">

    <!-- For syntax highlighting -->
    <!--<link rel="stylesheet" href="lib/css/zenburn.css">-->
    <!--<link rel="stylesheet" href="lib/css/solarized_light.css">-->
    <link rel="stylesheet" href="lib/css/github.css">

    <!-- If the query includes 'print-pdf', use the PDF print sheet -->
    <script>
        document.write( '<link rel="stylesheet" href="css/print/' + ( window.location.search.match( /print-pdf/gi ) ? 'pdf' : 'paper' ) + '.css" type="text/css" media="print">' );
    </script>

    <!--[if lt IE 9]>
    <script src="lib/js/html5shiv.js"></script>
    <![endif]-->
</head>

<body>

<div class="reveal architecture">
<div class="slides">

<section><!-------------------------- slide ------------------------>
    <h1>官网技术堆栈</h1>
    <h3>着重讲述官网后台管理技术堆栈</h3>
    <p>
        <strong>王士江</strong>
        <br/>
        <small>2015年01月23日</small>
    </p>
</section>

<section class="tech-stack"><!-------------------------- slide ------------------------>
    <h2>技术堆栈</h2>
    <div class="tech-stack-client">
        <div class="tech-stand"><span class="stand">Client</span></div>
        <div class="tech-used"><a href="#/html5">HTML5</a>/<a href="#/css3">CSS3</a>/<a href="#/javascript">JavaScript</a></div>
        <div class="tech-practise">Functional Test</div>
    </div>
    <div class="tech-stack-server-rest">
        <div class="tech-stand"><span class="stand"><a href="#/rest">REST</a></span></div>
        <div class="tech-used">Spring MVC</div>
        <div class="tech-practise">Domain Design, <a href="#/rest-test">Unit Test</a></div>
    </div>
    <div class="tech-stack-server-hibernate">
        <div class="tech-stand"><span class="stand">Persistence</span></div>
        <div class="tech-used">MyBatis</div>
        <div class="tech-practise">Integration Test</div>
    </div>
    <div class="tech-stack-db">
        <div class="tech-stand"><span class="stand">DataBase</span></div>
        <div class="tech-used">MySQL</div>
        <div class="tech-practise"><a href="#/db-migration">DB Migration</a></div>
    </div>
    <p>登陆认证部分，使用<a href="#/authentication">JWT</a>标准。</p>
    <p>新浪微博某架构师发表的<a target="_blank" href="http://www.infoq.com/cn/articles/weibo-platform-archieture">新浪微博架构文章</a></p>
</section>

<section><!-------------------------- slide ------------------------>
    <section>
        <h2>代码结构</h2>
        <h3>服务端Java代码结构</h3>
<pre><code data-trim>
    com.aly
    ├── api
    │   └── REST层API
    ├── controller
    │   └── Spring MVC的Controller
    ├── dao
    │   └── MyBatis的Mapper
    ├── domain
    │   └── ORM实体
    ├── security
    │   └── 安全认证部分代码
    ├── service
    │   └── 业务代码
    └── util
    └── 抽出的工具类
</code></pre>
    </section>

    <section>
        <h2>代码结构</h2>
        <h3>客户端HTML、CSS、Javascript代码结构</h3>
<pre><code data-trim>
    admin/
    ├── assets/
    │   ├── css
    │   │   └── 公用组件的样式
    │   ├── img
    │   │   └── 公用组件的图片
    │   ├── js
    │   │   └── 公用组件的JS库
    │   └── swfobject
    │       └── flash小插件
    ├── css/
    │   └── 各个页面样式
    ├── js/
    │   └── AngularJS各个模块
    ├── partials/
    │   └── angular-ui-router使用到的页面
    └── 工作页面
</code></pre>
    </section>
</section>

<section id="html5"><!-------------------------- slide ------------------------>
    <h2>HTML5</h2>
    <ul>
        <li>新增标签：<code>&lt;section&gt;</code>、<code>&lt;article&gt;</code>等等；</li>
        <li>本地存储：localStorage、sessionStorage、indexedDB等等；</li>
        <li>其他：WebSocket等等。</li>
    </ul>
<pre><code data-trim>
    &lt;!doctype html&gt;
    &lt;html lang=&quot;en&quot;&gt;
    &lt;head&gt;
    &lt;meta charset=&quot;UTF-8&quot;&gt;
    &lt;title&gt;Document&lt;/title&gt;
    &lt;/head&gt;
    &lt;body&gt;
    &lt;section&gt;
    &lt;h2&gt;官网后台管理演示文档&lt;/h2&gt;
    &lt;/section&gt;
    &lt;/body&gt;
    &lt;/html&gt;
</code></pre>
    <p><small>这个演示就是用HTML5写的，可以看看源代码。</small></p>
</section>

<section id="css3"><!-------------------------- slide ------------------------>
    <h2>CSS3</h2>
    <ul>
        <li>新增动画(CSS3 Animation)、切换(CSS3 Transition)、响应式布局（Media Query）等等支持；</li>
        <li>官网后台使用了Twitter的开源组件库——<a target="_blank" href="http://www.bootcss.com">Bootstrap</a>。</li>
    </ul>
<pre><code data-trim>
    .reveal .roll:hover span {
    background: rgba(0,0,0,0.5);

    -webkit-transform: translate3d( 0px, 0px, -45px ) rotateX( 90deg );
    -moz-transform: translate3d( 0px, 0px, -45px ) rotateX( 90deg );
    -ms-transform: translate3d( 0px, 0px, -45px ) rotateX( 90deg );
    transform: translate3d( 0px, 0px, -45px ) rotateX( 90deg );
    }
</code></pre>
    <p><small>这个演示就用到了CSS3，可以看看源代码。</small></p>
</section>

<section><!-------------------------- slide ------------------------>
    <section id="javascript">
        <h2>Javascript</h2>
        <ul>
            <li>使用AngularJS作为整个前端框架的核心库；</li>
            <li>其他辅助JS库：jQuery、jQuery Markdown插件、Angular UI、Angular Bootstrap、Angular ui-rooter等等。</li>
        </ul>
<pre><code data-trim>
    &lt;script src="assets/js/jquery.js"&gt;&lt;/script&gt;
    &lt;script src="../jslib/jquery.markdown-0.2.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/jquery-ui.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/jquery-ui-timepicker-addon.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/jquery-ui-timepicker-zh-CN.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/jquery.ztree.core.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/bootstrap.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/select2.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/mousetrap.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/mousetrap-global.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/angular.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/angular-ui.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/angular-ui-router.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/ui-bootstrap-tpls.js"&gt;&lt;/script&gt;
    &lt;script src="assets/js/ng-grid.js"&gt;&lt;/script&gt;
</code></pre>
    </section>
    <section id="angularjs">
        <h2>Angular JS</h2>
        <ul>
            <li>Google工程师开发的JS框架，旨在简化前端开发的难度；</li>
            <li>让浏览器“学会识别”自定义的标签、属性、样式等等。</li>
        </ul>
<pre><code data-trim>
    &lt;tabs&gt;&lt;!-- angular-bootstrap指令版 --&gt;
    &lt;pane heading="修改密码"&gt;
    ...
    &lt;/pane&gt;
    &lt;pane heading="用户管理"&gt;
    ...
    &lt;/pane&gt;
    &lt;pane heading="权限管理"&gt;
    ...
    &lt;/pane&gt;
    &lt;/tabs&gt;

    &lt;ul class="nav nav-tabs" id="myTab"&gt;&lt;!-- Bootstrap原生版 --&gt;
    &lt;li class="active"&gt;&lt;a href="#change-password"&gt;修改密码&lt;/a&gt;&lt;/li&gt;
    &lt;li&gt;&lt;a href="#user-manage"&gt;用户管理&lt;/a&gt;&lt;/li&gt;
    &lt;li&gt;&lt;a href="#authorization"&gt;权限管理&lt;/a&gt;&lt;/li&gt;
    &lt;/ul&gt;
    &lt;div class="tab-content"&gt;
    &lt;div class="tab-pane active" id="change-password"&gt;...&lt;/div&gt;
    &lt;div class="tab-pane" id="user-manage"&gt;...&lt;/div&gt;
    &lt;div class="tab-pane" id="authorization"&gt;...&lt;/div&gt;
    &lt;/div&gt;
    &lt;script&gt;
    $(function () {
    $('#myTab a:last').tab('show');
    })
    &lt;/script&gt;
</code></pre>
    </section>
</section>

<section>
    <section id="rest"><!-------------------------- slide ------------------------>
        <h2>REST</h2>
        <ul>
            <li>使用Spring MVC 4对<a target="_blank" href="http://www.infoq.com/articles/rest-introduction">REST</a>的支持；</li>
            <li>尽量使用Spring的注解进行配置，而且Spring社区官方也推荐使用Java Config(注解)，减轻配置xml的工作量。</li>
        </ul>
<pre><code class="java" data-trim>
    /**
    * 游戏资源类。
    */
    @RestController
    @RequestMapping("/api")
    public class GamesResource {
    /**
    * 获取全部游戏。
    * @return 全部游戏
    */
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List&lt;Games&gt; getAllGames() {
    return gamesService.getAllGames();
    }
    ... ...
    }
</code></pre>
    </section>
    <section>
        <h2>关于REST</h2>
        <ul>
            <li><a target="_blank" href="http://www.infoq.com/articles/rest-introduction">A Brief Introduction to REST</a> </li>
            <li>API（应用编程接口）定义——不光要接浏览器，还要接移动设备的各种APP。</li>
        </ul>
        <p style="height: 500px;"><img src="images/RESTful_Application.jpg"/></p>
    </section>
</section>
<section id="rest-test"><!-------------------------- slide ------------------------>
    <h2>Unit Test</h2>
    <ul>
        <li>TDD(Test Driven Development，测试驱动开发)，先写一个测试，表达出用意，再用代码将功能实现；</li>
    </ul>
</section>

<section id="db-migration"><!-------------------------- slide ------------------------>
    <h2>DB Migration</h2>
    <ul>
        <li>使用了Google工程师开发的<a target="_blank" href="http://flywaydb.org">FlywayDB</a>，进行数据库脚本管理。</li>
    </ul>
<pre><code data-trim>
    resources/db/migration/
    ├── V1__Create_Initial_Tables.sql
    └── V2__Init_Data.sql
</code></pre>
</section>

<section id="authentication"><!-------------------------- slide ------------------------>
    <h2>认证处理——JWT</h2>
    <ul>
        <li>使用JWT(JSON Web Token)标准作为认证部分实现；</li>
        <li>JWT specification group under IETF is available at
            <a target="_blank" href="https://datatracker.ietf.org/doc/draft-ietf-oauth-json-web-token/">https://datatracker.ietf.org/doc/draft-ietf-oauth- json-web-token</a></li>
        <li>服务端关闭了JSP的Session，不使用Cookie（会有CORS问题）；</li>
        <li>使用HTML5的localStorage来存储JWT文本。</li>
    </ul>
<pre><code data-trim>
    //JWT Token
    eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0MjI0MTM0NTUsInN1YiI6ImpvaG4iLCJuYmYiOjE0MjE5ODE0NTUsImF1ZCI6Imh0dHA6XC9cL3d3dy41YWdhbWUuY29tXC9hZG1pbiIsImlzcyI6Imh0dHA6XC9cL3d3dy41YWdhbWUuY29tIiwianRpIjoiMzk5NTlmOWQtYzRlOS00NGQ2LWJjOTgtMmFiNDFlZjlkYTI1IiwiaWF0IjoxNDIxOTgxNDU1fQ.CX2Cmqwo29ju6aSX_wOd6t4kRhscWwhEGSlmFxTw6bc

    &lt;%/* 关闭Session */%&gt;
    &lt;%@ page session="false"%&gt;
</code></pre>
</section>

<section><!-------------------------- slide ------------------------>
    <h2>The End</h2>
</section>

</div><!-- slides -->
</div><!-- reveal -->

<script src="lib/js/head.min.js"></script>
<script src="js/reveal.min.js"></script>

<script>

    // Full list of configuration options available here:
    // https://github.com/hakimel/reveal.js#configuration
    Reveal.initialize({
        controls: true,
        progress: true,
        history: true,
        center: true,

        theme: Reveal.getQueryHash().theme, // available themes are in /css/theme
        transition: Reveal.getQueryHash().transition || 'default', // default/cube/page/concave/zoom/linear/fade/none

        // Optional libraries used to extend on reveal.js
        dependencies: [
            { src: 'lib/js/classList.js', condition: function() { return !document.body.classList; } },
            { src: 'plugin/markdown/marked.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
            { src: 'plugin/markdown/markdown.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
            { src: 'plugin/highlight/highlight.js', async: true, callback: function() { hljs.initHighlightingOnLoad(); } },
            { src: 'plugin/zoom-js/zoom.js', async: true, condition: function() { return !!document.body.classList; } },
            { src: 'plugin/notes/notes.js', async: true, condition: function() { return !!document.body.classList; } }
            // { src: 'plugin/search/search.js', async: true, condition: function() { return !!document.body.classList; } }
            // { src: 'plugin/remotes/remotes.js', async: true, condition: function() { return !!document.body.classList; } }
        ]
    });

</script>

</body>
</html>
