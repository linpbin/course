$(function(){
   var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);
    var $renotice = $("#renotice");
    var courseid = sessionStorage.getItem("courseid");
    courseid = JSON.parse(courseid);
    
    $renotice.bind("click",function () {
        var content = $("#contents").val();
        var pageparam={
            "context":content,
            "courseId":courseid,
        }
        $.ajax({
            type : "post",
            url : "http://localhost:8081/releaseNotice",
            data: JSON.stringify(pageparam),
            contentType : "application/json;charset=utf-8",
            dataType:"json",
            success:function (commResult) {
                alert("发布成功！！！")
                window.location.href="teareleasenotice.html"
            },
            error:function (commResult) {
                alert("发布失败！！");
                window.location.href="teareleasenotice.html";
            }
        })
    })
})