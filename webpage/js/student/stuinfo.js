 $(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);
        var studnetInfoList =sessionStorage.getItem("studnetInfo");
        studnetInfoList=JSON.parse(studnetInfoList);
        if (studnetInfoList.resultCode==0) {
           let studnetInfoLists = studnetInfoList.data;
           var list = '';
           list+= `<table class="table"><tbody><thead>`
            list+=`<tr>
            <th>基本信息</th>
            </tr>
            <tr>
            <td>学号</td>
            <td>${studnetInfoLists.id}</td>
            </tr>
            <tr>
            <td>姓名</td>
            <td>${studnetInfoLists.studentName}</td>
            </tr>
            <tr>
            <td>系别</td>
            <td>${studnetInfoLists.studentFaculty}</td>
            </tr>
            <tr>
            <td>专业</td>
            <td>${studnetInfoLists.studentProfession}</td>
            </tr>
            <tr>
            <td>性别</td>
            <td>${studnetInfoLists.sex}</td>
            </tr>
            <tr>
            <td>入学年份</td>
            <td>${studnetInfoLists.studentEnrollmentyear}</td>
            </tr>
            <tr>
            <td>班级</td>
            <td>${studnetInfoLists.clazz.id}</td>
            </tr>
            <tr>
            <td>班级名称</td>
            <td>${studnetInfoLists.clazz.clazzName}</td>
            </tr>`
            list+=`</thead></tbody></table>`
            $("#stuinfo").html(list);
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
            $("#stuinfo").html(list);
        }

})