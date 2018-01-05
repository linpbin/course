 $(function () {
            // home nav
            $("#main").click(function(){
               window.location.href='stuMain.html';
           })
            // var $main = $("#main");
            // $main.bind("click",function () {

            // })
            //notice nav
            var $notice = $("#notice");
            $notice.bind("click",function () {

                $.ajax({
                    type : "get",
                    url : "http://localhost:8081/stuAnnouncement",
                    dataType:"json",
                    success:function (commResult) {
                        var announcementList = commResult;
                        announcementList = JSON.stringify(announcementList);
                        sessionStorage.setItem("announcementList",announcementList);
                        window.location.href='stuAnnouncement.html';  
                    },
                    error:function (commResult) {
                        window.location.href='stuMain.html';
                    }
                })
            })
            var $info =$("#info");
            var student = sessionStorage.getItem("student");
            student = JSON.parse(student);
            var userId = student.id;
            console.log(userId);
            $info.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/studnetInfo",
                    data: JSON.stringify(userId),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var studnetInfo =commResult;
                        studnetInfo=JSON.stringify(studnetInfo);
                        sessionStorage.setItem("studnetInfo",studnetInfo);
                        window.location.href='stuInfo.html';
                    },
                    error:function(commResult){
                        window.location.href='stuMain.html';
                    }
                })
            })
            //modify password nav

            $("#modify").click(function(){
               window.location.href='stuModifyPw.html';
           })
            //my course
            var $course =$("#course");
            var student = sessionStorage.getItem("student");
            student = JSON.parse(student);
            var userId = student.id;
            console.log(userId);
            $course.bind("click",function(){
                $.ajax({
                    type :"post",
                    url:"http://localhost:8081/showCourseList",
                    data: JSON.stringify(userId),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        console.log("success");
                        console.log(commResult);
                        var courseList =commResult;
                        courseList=JSON.stringify(courseList);
                        sessionStorage.setItem("courseList",courseList);
                        window.location.href='stuCourse.html';
                    },
                    error:function(commResult){
                        window.location.href='stuMain.html';
                    }
                })
            })


        })

