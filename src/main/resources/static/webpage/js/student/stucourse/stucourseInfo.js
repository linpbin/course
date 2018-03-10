 $(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);

        var courseinfo =sessionStorage.getItem("courseinfo");
        courseinfo=JSON.parse(courseinfo);
        if (courseinfo.resultCode==0) {
           let courseinfos = courseinfo.data;
           var list = '';
           list+= `<table class="table"><tbody><thead>`
            list+=`<tr>
            <th>课程介绍</th>
            </tr>
            <tr>
            <td>课程编号</td>
            <td>${courseinfos.id}</td>
            </tr>
            <tr>
            <td>课程名称</td>
            <td>${courseinfos.courseName}</td>
            </tr>
            <tr>
            <td>任课教师</td>
            <td>${courseinfos.teacherId.teacherName}</td>
            </tr>
            <tr>
            <td>课程介绍</td>
            <td>${courseinfos.courseDescribe}</td>
            </tr>`
            list+=`</thead></tbody></table>`
            $("#courseinfos").html(list);
        }else{
            let studnetInfoLists = studnetInfoList.resultMsg;
            var list = '';
           list+= `  <table class="table"><tbody>
           `
            list+=`
            <tr>
            <td >
            ${studnetInfoLists}
            </td>
        
            </tr>
            `
            list+=`</tbody></table>`
            $("#courseinfos").html(list);
        }

})