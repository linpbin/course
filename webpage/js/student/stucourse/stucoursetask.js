$(function () {
        var studentname = sessionStorage.getItem("student");
        studentname = JSON.parse(studentname);
        $("#name").html(studentname.studentName);
        var coursetask =sessionStorage.getItem("coursetask");
        coursetask=JSON.parse(coursetask);
        if (coursetask.resultCode==0) {
         let coursetasks = coursetask.data;
         var list = '';
         list+= `  <table class="table"><tbody><thead>
         <tr>
         <th>作业名称</th>
         <th>作业描述</th>
         <th>截至时间</th>
         <th>作业下载</th>
         </tr>`
         for(var i=0;i<coursetasks.length;i++){
            list+=`
            <tr>
            <td >
            ${coursetasks[i].taskName}
            </td>
            <td >
            ${coursetasks[i].taskDescribe}
            </td>
             <td >`
             list+=formatDateTime(coursetasks[i].taskDeadline);
           
            list+=`</td>           
            <td><a href="javascript:void(0)" onclick="downloadcoursetask(${coursetasks[i].id})">下载</a></td>
            </tr>
            `
        }
        list+=`</thead></tbody></table>`
        $("#coursetasks").html(list);
    }else{
       let coursetasks = coursetask.resultMsg;
       var list = '';
       list+= `  <table class="table"><tbody>
       `
       
       list+=`
       <tr>
       <td >
       ${coursetasks}
       </td>
       
       </tr>
       `
       
       list+=`</tbody></table>`
       $("#coursetasks").html(list);
   }
})
function downloadcoursetask(id){
  $.ajax({
        type :"post",
        url:"http://localhost:8081/downloadCoursetask",
        data: JSON.stringify(id),
        contentType : "application/json;charset=utf-8",
        dataType:"json",
        success:function(commResult){
           
        },
        error:function(commResult){
            alert("下载失败！");
        }
    })

}
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
}; 

