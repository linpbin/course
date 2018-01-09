 $(function () {
            // home nav
            $("#main").click(function(){
               window.location.href='teaMain.html';
           })
            // var $main = $("#main");
            // $main.bind("click",function () {

            // })
            //notice nav
            var $notice = $("#notice");
            $notice.bind("click",function () {

                $.ajax({
                    type : "get",
                    url : "http://localhost:8081/teaAllAnnouncement",
                    dataType:"json",
                    success:function (commResult) {
                        var announcementList = commResult;
                        announcementList = JSON.stringify(announcementList);
                        sessionStorage.setItem("allannouncementList",announcementList);
                        window.location.href='teaAnnouncement.html';  
                    },
                    error:function (commResult) {
                        window.location.href='teaMain.html';
                    }
                })
            })
            var $info =$("#info");
            var teacher = sessionStorage.getItem("teacher");
            teacher = JSON.parse(teacher);
            var userId = teacher.id;
            $info.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/teacherInfo",
                    data: JSON.stringify(userId),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var teacherInfo =commResult;
                        teacherInfo=JSON.stringify(teacherInfo);
                        sessionStorage.setItem("teacherInfo",teacherInfo);
                        window.location.href='teaInfo.html';
                    },
                    error:function(commResult){
                        window.location.href='teaMain.html';
                    }
                })
            })
            //modify password nav

            $("#modify").click(function(){
               window.location.href='teaModifyPw.html';
           })
            //my course
            var $course =$("#course");
            var teacher = sessionStorage.getItem("teacher");
            teacher = JSON.parse(teacher);
            var userId = teacher.id;
            $course.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/showTeaCourseList",
                    data: JSON.stringify(userId),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var courseList =commResult;
                        courseList=JSON.stringify(courseList);
                        sessionStorage.setItem("courseList",courseList);
                        window.location.href='teaCourse.html';
                    },
                    error:function(commResult){
                        window.location.href='teaMain.html';
                    }
                })
            })
var $layout=$("#layout");
    $layout.bind("click",function(){
        sessionStorage.clear();
        window.location.href="../main.html"
    })

        })

