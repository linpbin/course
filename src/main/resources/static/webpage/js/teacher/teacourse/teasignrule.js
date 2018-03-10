$(function(){
   var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);
    $("#startTime").jeDate({
        format: "YYYY-MM-DD hh:mm:ss"
    });
     $("#endTime").jeDate({
        format: "YYYY-MM-DD hh:mm:ss"
    });
    var courseid = sessionStorage.getItem("courseid");
    courseid = JSON.parse(courseid);


    $("#courseId").val(courseid);
    $resignrule=$("#resignrule");
    $resignrule.bind('click',function(){
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var params = {
            "startTime":startTime,
            "endTime":endTime,
            "courseId":courseid,
        }
         $.ajax({
            type :"post",
            url:"http://localhost:8081/signrule",
            data: JSON.stringify(params),
            contentType : "application/json;charset=utf-8",
            dataType:"json",
            success:function(commResult){
                if (commResult.resultCode==0) {
                    var signruleid=commResult.data;
                    sessionStorage.setItem("signruleid",signruleid.id);
                    window.location.href="qrcord.html";
               }else{
                    alert(commResult.resultMsg);
               }
            },
            error:function(commResult){
                 window.location.href="teacourseHome.html"
            }
         })
    })
     
})