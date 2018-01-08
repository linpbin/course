 $(function () {
        var $login = $("#login");
        $login.bind("click",function () {
            var username = $("#username").val();
            var password = $("#inputPassword").val();
            var logintype = $("#logintype").val();
            var loginJson = {
                "username":username,
                "password":password,
                "logintype":logintype,
            }
            $.ajax({
                type : "post",
                url : "http://localhost:8081/login",
                data :JSON.stringify(loginJson),
                contentType : "application/json;charset=utf-8",
                dataType:"json",
                success:function (commResult) {
                   if (commResult.resultCode == 0 && logintype=="Student"){
                       var studentinfo = commResult.data;
                       studentinfo = JSON.stringify(studentinfo);
                       sessionStorage.setItem("student",studentinfo);
                      window.location.href='student/stuMain.html'
                   }else if (commResult.resultCode == 0 && logintype=="Teacher"){
                       sessionStorage.setItem("teacher",commResult.data);
                       window.location.href="http://localhost:8081/teacher/teaMain.html";
                   }
                },
                error:function (commResult) {
                    sessionStorage.setItem("loginerror",commResult.resultMsg);
                   window.location.href="main.html";
                }
            });
             $.ajax({
                    type : "get",
                    url : "http://localhost:8081/stuAnnouncement",
                    dataType:"json",
                    success:function (commResult) {
                        var announcementList = commResult;
                        announcementList = JSON.stringify(announcementList);
                        sessionStorage.setItem("announcementList",announcementList);
          
                    },
                    error:function (commResult) {
                       
                    }
                });
            $.ajax({
                    type :"post",
                    url:"http://localhost:8081/studentInfo",
                    data: JSON.stringify(username),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                        var studnetInfo =commResult;
                        studnetInfo=JSON.stringify(studnetInfo);
                        sessionStorage.setItem("studnetInfo",studnetInfo);
                      
                    },
                    error:function(commResult){

                    }
                     
                })
             $.ajax({
                    type :"post",
                    url:"http://localhost:8081/showCourseList",
                    data: JSON.stringify(username),
                    contentType : "application/json;charset=utf-8",
                    dataType:"json",
                    success:function(commResult){
                
                        var courseList =commResult;
                        courseList=JSON.stringify(courseList);
                        sessionStorage.setItem("courseList",courseList);
              
                    },
                    error:function(commResult){
                  
                    }
                })
        })
    })