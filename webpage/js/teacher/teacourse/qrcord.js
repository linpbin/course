 $(function() {
 		var signruleid = sessionStorage.getItem("signruleid")
        jQuery('#qrcode').qrcode("http://127.0.0.1:8081/signrecord/"+signruleid);  
    }); 