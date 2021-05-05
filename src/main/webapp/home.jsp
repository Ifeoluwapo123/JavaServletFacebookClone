<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.FacebookClone.dbConnectionProvider.DbConnection" %>
<%@ page import="com.example.FacebookClone.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.FacebookClone.DOA.PostDatabase" %>
<%@ page import="com.example.FacebookClone.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Facebook Home Page</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>timeline</title>
    <!-- JQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"
    ></script>
    <script type="javascript" src="js/home.js"></script>
    <link rel="stylesheet" href="css/home.css" />
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    PostDatabase postData = new PostDatabase(DbConnection.getConnection());

    List<Post> posts = postData.getPosts(user);

    if(session.getAttribute("message") != null){%>
        <div class="alert alert-primary" role="alert">
            <%=session.getAttribute("message").toString()%>
        </div>
    <%}

    if(user == null){
        session.setAttribute("Registration Error", "!!!Please Login first");
        response.sendRedirect("index.jsp");
    }else{%>
<!-- ============================================================================================================== -->

<!-- ============================================================================================================== -->

        <nav class="navbar navbar-expand-lg navbar-success bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Facebook</a>
                <form class="d-flex">
                    <input
                            class="me-2 searchbar"
                            type="search"
                            placeholder="Search Facebook...."
                            aria-label="Search"
                    />
                </form>
                <button
                        class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"
                            ><img src="./image/marketplace.png" class="pictures alt="
                            /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"
                            ><img src="./image/group.png" class="pictures alt="
                            /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"
                            ><img src="./image/watch.png" class="pictures alt=" />
                                <p class="yourfeed"></p
                                ></a>
                        </li>
                        <li class="nav-item"></li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"
                            ><img src="./image/friends.png" class="pictures alt="
                            /></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a
                                    class="nav-link dropdown-toggle"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                            ><img src="./image/dipson.jpg" class="pix" alt="" />
                                <%=user.getFirstname() +" "+ user.getSurname() %>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="index.jsp">Logout</a></li>
                                <li><a class="dropdown-item" href="#">Setting</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#">friends</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <a class="nav-link active" aria-current="page" href="#"
                        ><img src="./image/marketplace.png" class="pictures alt="
                        /></a>
                        <a class="nav-link active" aria-current="page" href="#"
                        ><img src="./image/group.png" class="pictures alt="
                        /></a>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3">
                    <div class="fix">
                        <div>
                            <img src="./image/dipson.jpg" class="pix" alt="" />
                            <p class="yourfeed"><%=user.getFirstname() +" "+ user.getSurname() %> Show</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/love.png " class="pictures" alt="" />
                            <p class="yourfeed">Covid-19 Information Centre</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/friends.png" class="pictures alt=" />
                            <p class="yourfeed">Friends</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/group.png" class="pictures alt=" />
                            <p class="yourfeed">Groups</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/marketplace.png" class="pictures alt=" />
                            <p class="yourfeed">Marketplace</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/watch.png" class="pictures alt=" />
                            <p class="yourfeed">Watch</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/Events.png" class="pictures alt=" />
                            <p class="yourfeed">Event</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/marketplace.png" class="pictures alt=" />
                            <p class="yourfeed">Memory</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/Saved.png " class="pictures alt=" />
                            <p class="yourfeed">save</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/Pages.png " class="pictures alt=" />
                            <p class="yourfeed">Pages</p>
                        </div>
                        <div class="yourfeed1">
                            <img src="./image/semore.png" class="pictures alt=" />
                            <p class="yourfeed">See more</p>
                        </div>
                        <p class="text">Your Shortcuts</p>
                        <img src="" alt="" />
                        <p class="text">Baba Kamo Alariya</p>
                        <p>
                            <small
                            >Privacy · Terms · Advertising · Ad Choices · Cookies · ·
                                Facebook © 2021</small
                            >
                        </p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="scroller like-icon">
                        <%
                            for (Post each:posts) {%>
                                <div class="linear">
                                    <div class="titleText">
                                        <h2><%=each.getTitle()%></h2>
                                        <p><span style="font-weight: bold; color:#81adef; font-size: 1.4em;margin-right: 5px">Author:</span>
                                            <%=each.getName()%></p>
                                    </div>
                                    <img src="./image/<%=each.getImageName()%>" class="majorpix" alt="" />
                                </div>
                                <div class="body">
                                    <p><%=each.getBody()%></p>
                                </div>
                                <hr>
                                <div class="comment_like_share mb-2">
                                    <div class="like">
                                        <%
                                            if(each.isLikedPost()){%>
                                                <i onclick="like(<%=each.getId()%>, <%=user.getId()%>)" id="<%=each.getId()%>"
                                                   style="color: #1977f2" class="fa fa-thumbs-o-up" aria-hidden="true">
                                                    <span id="like"><%=each.getNoLikes()%></span></i>
                                                <!-- <span>Like</span> -->
                                            <%}else{%>
                                                <i onclick="like(<%=each.getId()%>, <%=user.getId()%>)" id="<%=each.getId()%>"
                                                   style="color: #fff" class="fa fa-thumbs-o-down" aria-hidden="true">
                                                    <span id="like1"><%=each.getNoLikes()%></span></i>
                                                <!-- <span>Like</span> -->
                                            <%}
                                        %>
                                    </div>
                                    <div class="comment">
                                        <i class="fa fa-commenting-o" aria-hidden="true" onclick="com(<%=each.getId()%>)">
                                            <span><%=each.getNoComments()%>
                                        </i>

                                        <!-- <span>Comment</span> -->
                                    </div>
                                    <div class="edit">
                                        <i class="fa fa-edit" aria-hidden="true" onclick="edit(<%=each.getId()%>)">
                                        </i>
                                        <!-- <span>Edit</span> -->
                                    </div>
                                    <div class="delete">
                                        <i class="fa fa-remove" aria-hidden="true" id="delete" onclick="del(<%=each.getId()%>)"></i>
                                        <!-- <span>Delete</span> -->
                                    </div>
                                </div>
                                <div class="row mb-2">
                                   <form action="/CommentServlet" method="POST">
                                        <input style="display:none" name="postId" value="<%=each.getId()%>"/>
                                        <textarea
                                                name="comment"
                                                class="form-control drag"
                                                placeholder="What's on your mind <%=user.getSurname()%>?"
                                        ></textarea>
                                       <button type="summit" class="btn btn-primary btn-md mt-3 btn-block">
                                               Comment
                                       </button>
                                   </form>
                                </div>
                            <%}
                        %>
                    </div>
                </div>
                <div class="col-lg-3 third">
                    <div class="postArea">
                        <form action="/PostServlet" method="POST" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="post-title" class="form-label">Title</label>
                                <input
                                        type="text"
                                        name="title"
                                        class="form-control"
                                        id="post-title"
                                        placeholder="Post Title"
                                        required
                                />
                            </div>
                            <div class="mb-2">
                        <textarea
                                style="border-radius: 30px"
                                name="body"
                                id="post"
                                cols="30"
                                rows="2"
                                class="form-control"
                                aria-describedby="emailHelp"
                                placeholder="Whats on your mind <%=user.getSurname()%>"
                                required
                        ></textarea>
                            </div>
                            <div class="row post-buttons mb-4">
                                <div class="col-md-6 col-btn">
                                    <label class="form-label" for="customFile">Photo</label>
                                    <input type="file" name="file" class="form-control" id="customFile" required/>
                                </div>
                                <div class="col-md-6 col-btn">
                                    <p></p>
                                    <button type="submit" class="btn btn-primary btn-md mt-3 btn-block">
                                        Post
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer></footer>
        <script
                src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
                integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
                crossorigin="anonymous"
        ></script>
        <script
                src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
                integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
                crossorigin="anonymous"
        ></script>
        <script src="https://use.fontawesome.com/aed9ef824b.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    //edit post
    function edit(postId){
        console.log("working now");
        window.location.href = "/edit.jsp?post="+postId;
    }

    //comment on post
    function com(postId){
        console.log("working now");
        window.location.href = "/comment.jsp?post="+postId;
    }

    //like on post
    function like(postId, userId){
        //fa fa-thumbs-o-up
        const like = document.getElementById(postId).className;
        const URL = "/LikeServlet";

        if(like == "fa fa-thumbs-o-down"){
            let l = document.getElementById("like");
            let a = Number(l.innerText);
            a+=1;

            console.log("a", a)
            document.getElementById("like").innerText = a+"";
            document.getElementById(postId).className = "fa fa-thumbs-o-up";
            document.getElementById(postId).style.color = "#1977f2";
            const data = {postId, userId, "action": 1}

            ajaxCall(URL, data, 1);
        }
        else {
            let l = document.getElementById("like");
            let a = Number(l.innerText);
            a-=1;
            console.log("b", a)
            document.getElementById("like1").innerText = a+"";
            document.getElementById(postId).className = "fa fa-thumbs-o-down";
            document.getElementById(postId).style.color = "#fff";
            const data = {postId, userId, "action": 0}

            ajaxCall(URL, data, 0);
        }

    }

    function ajaxCall(url, dataCall, action){
        $.ajax({
            type: 'POST',
            url: url,
            data: dataCall,

            success: function(data){
                console.log(data);
            },
            error: function(){
                alert('error liking');
            }
        });
    }

    //delete post
    function del(postId){
        console.log("working");

        const delPost = confirm("Are you sure you want to delete post");

        if(delPost){
            $.ajax({
                type: 'POST',
                url: '/DeletePostServlet',
                data: {"postId": postId},

                success: function(data){
                    console.log(data);

                    //location.href = "/home.jsp";
                    window.location.reload();
                },
                error: function(){
                    alert('error deleting post');
                }
            });

            alert("Post Successfully deleted");
        }
    }
</script>
    <%}
%>
</body>
</html>


