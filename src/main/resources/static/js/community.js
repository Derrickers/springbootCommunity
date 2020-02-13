/**
 * post:Json
 * 评论功能
 */
function commentToTarget(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=f4e675ebc5b8c6f602e6&redirect_url=http://localhost:8877/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

/**
 * 评论问题
 */
function commentQuestionPost() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentToTarget(questionId, 1, content);
}

/**
 * 回复评论
 * @param e
 */
function commentCommentPost(e) {
    var id = e.getAttribute("data-id");
    var content = $("#input-" + id).val();
    commentToTarget(id, 2, content);
}

/**
 * 展开二级评论
 * @param e
 */
function pullComment(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    //获取展开状态
    var state = e.getAttribute("data-show");
    if (state) {
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-show");
        e.classList.remove("onUse");
    } else {
        //获得父标签
        var subCommentContainer = $("#comment-"+id);

        if(subCommentContainer.children().length == 1){
            //获取请求
            $.getJSON("/comment/" + id, function (data) {
                moment.locale();
                $.each(data.data, function (index, comment) {

                    var mediaLeftElement = $("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-rounded",
                        "src":comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>",{
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.content
                    })).append($("<div/>",{
                        "class":"menu"
                    }).append($("<span/>",{
                        "class":"pull-right text-desc",
                        "html":moment(comment.gmtCreate).startOf('hour').fromNow()
                    })));


                    var mediaElement = $("<div/>",{
                        "class":"media"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var commentBodyElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-body"
                    });
                    commentBodyElement.append(mediaElement);
                    subCommentContainer.prepend(commentBodyElement);
                });
            });
        }
        //展开二级评论
        comments.addClass("in");
        //标记展开状态
        e.setAttribute("data-show", "true");
        e.classList.add("onUse");
    }
}

/**
 * 选中标签
 * @param tag
 */
function selectTag(tag) {
    var previous = $("#tag").val();
    if(previous.indexOf(tag) == -1){
        if(previous){
            $("#tag").val(previous+'/'+tag);
        }else{
            $("#tag").val(tag);
        }
    }
}

function showTags() {
    $("#tags").show();
}

