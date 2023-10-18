$(function(){
   $.ajax({
      type: 'post',
      url: '/springMavenNCP/user/getUploadList',
      dataType: 'json',
      success: function(data){
         console.log(JSON.stringify(data));
         
         var result;
         $.each(data, function(index, item){
            result = `<tr>` +
                   `<td align = "center">` + item.seq + `</td>` +
                   `<td align = "center">` +
                      `<img src="https://kr.object.ncloudstorage.com/bitcamp-edu-bucket-105/storage/` + item.imageFileName + 
                      `" style="width: 70px; height: 70px;" alt="` + item.imageName + `">` +
                   `</td>` +
                   `<td align="center">` + item.imageOriginalName + `</td>` +
                   `</tr>`;
            
            $('#imageListTable').append(result);
         });
      },
      error: function(e){
         console.log(e);
      }
    });
});
/* 혁의 네이버클라우드 오브젝트 스토리지 버킷의 스토리지 경로
https://kr.object.ncloudstorage.com/bitcamp-edu-bucket-105/storage/
*/      