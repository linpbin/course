 $(function () {
        var teachername = sessionStorage.getItem("teacher");
        teachername = JSON.parse(teachername);
        $("#name").html(teachername.teacherName);
        var teacherInfoList =sessionStorage.getItem("teacherInfo");
        teacherInfoList=JSON.parse(teacherInfoList);
        console.log(teacherInfoList);
        if (teacherInfoList.resultCode==0) {
           let teacherInfoLists = teacherInfoList.data;
           var list = '';
           list+= `<table class="table"><tbody><thead>`
            list+=`<tr>
            <th>基本信息</th>
            </tr>
            <tr>
            <td>用户名</td>
            <td>${teacherInfoLists.id}</td>
            </tr>
            <tr>
            <td>姓名</td>
            <td>${teacherInfoLists.teacherName}</td>
            </tr>
            <tr>
            <td>email</td>
            <td>${teacherInfoLists.teacherEmail}</td>
            </tr>
            <tr>
            <td>办公电话</td>
            <td>${teacherInfoLists.teacherPhone}</td>
            </tr>
            <tr>
            <td>办公地点</td>
            <td>${teacherInfoLists.teacherWorkingplace}</td>
            </tr>
            <tr>
            <td>办公时间</td>
            <td>${teacherInfoLists.teacherWorkingTime}</td>
            </tr>
            <tr>
            <tr>
            <td>登录密码</td>
            <td>${teacherInfoLists.teacherPassword}</td>
            </tr>`
            list+=`</thead></tbody></table>`
            $("#teainfo").html(list);
        }else{
            let teacherInfoLists = teacherInfoList.resultMsg;
           var list = '';
           list+= `  <table class="table"><tbody>
           `
            list+=`
            <tr>
            <td >
            ${teacherInfoLists}
            </td>
        
            </tr>
            `
            list+=`</tbody></table>`
            $("#teainfo").html(list);
        }

})