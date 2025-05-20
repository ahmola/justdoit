let picture = {
    init:function(){
        $("#sendButton").on("click", ()=>{
            this.send();
        })
    },

    send:function(){
        var form = $("#fileSender")[0];
        var formData = new FormData(form);

        $.ajax({
            type:"POST",
            url:"/picture/send",
            data:formData,
            contentType: false,
            processData: false,
            cache: false
        }).done((resp)=>{
            alert(resp);
        }).fail((error)=>{
            alert("전송 실패!");
        });
    }
}

picture.init();