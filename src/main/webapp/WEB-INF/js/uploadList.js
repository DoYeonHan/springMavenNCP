$(function(){
	$.ajax({
		type: 'post',
		url: '/springMavenNCP/user/getUploadList',
		dataType: 'json',
		success: function(data){
			console.log(data)
			$.each(data, function(index, items){
				var result = `<tr>` +
						`<td align="center">` + items.seq + `</td>` +
						`<td align="center"><img src="https://kr.object.ncloudstorage.com/bitcamp-edu-bucket-92/storage/`+ items.imageFileName + `
						"alt="` +items.imageName+ `" style="width:70px;"> </td>` +
						`<td align="center">` + items.imageOriginalName + `</td>` +
						`</tr>`;
				$('#listTable').append(result);
			})
		},
		error: function(e) {
			
		}
	})
})