 $(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);

        var courseteacherinfo =sessionStorage.getItem("courseteacherinfo");
        courseteacherinfo=JSON.parse(courseteacherinfo);
        if (courseteacherinfo.resultCode==0) {
           let courseteacherinfos = courseteacherinfo.data;
           var list = '';
           list+= `<table class="table"><tbody><thead>`
            list+=`<tr>
            <th>教师信息</th>
            </tr>
            <tr>
            <td>教师姓名</td>
            <td>${courseteacherinfos.teacherName}</td>
            </tr>
            <tr>
            <td>Email</td>
            <td>${courseteacherinfos.teacherEmail}</td>
            </tr>
            <tr>
            <td>办公电话</td>
            <td>${courseteacherinfos.teacherPhone}</td>
            </tr>
            <tr>
            <td>办公地点</td>
            <td>${courseteacherinfos.teacherWorkingplace}</td>
            </tr>
            <tr>
            <td>办公时间</td>
            <td>${courseteacherinfos.teacherWorkingtime}</td>
            </tr>
            `
            list+=`</thead></tbody></table>`
            $("#classteacher").html(list);
        }else{
            let courseteacherinfos = courseteacherinfo.resultMsg;
           var list = '';
           list+= `  <table class="table"><tbody>
           `
            list+=`
            <tr>
            <td >
            ${courseteacherinfos}
            </td>
        
            </tr>
            `
            list+=`</tbody></table>`
            $("#classteacher").html(list);
        }

})