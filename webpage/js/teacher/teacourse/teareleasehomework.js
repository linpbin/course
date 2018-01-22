$(function(){
    $("#taskdeadline").jeDate({
        format: "YYYY-MM-DD hh:mm:ss"
    });
    var courseid = sessionStorage.getItem("courseid");
    courseid = JSON.parse(courseid);


    $("#courseId").val(courseid);
    $release=$("#release")
    $release.bind('click',function(){
        var formData=new FormData($("#form-file")[0]);
         $.ajax({
            type : "post",
                    url : "http://localhost:8081/releaseHomework",
                    type:"post",
                    data:formData,
                    contentType:false,
                    processData:false,
                    success:function (commResult) {
                       
                    },
                    error:function (commResult) {
                       window.location.href="teaCourseTask.html"
                    }
         })
    })
     
})