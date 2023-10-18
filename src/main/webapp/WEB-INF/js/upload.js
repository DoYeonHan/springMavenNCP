$(function(){
	$('#uploadBtn').click(function(){
		$('#imageNameDiv').empty();
		$('#imageContentDiv').empty();
		
		if($('#imageName').val() == '') {
			$('#imageNameDiv').text('상품명 입력');
			$('#imageName').focus();
			
		}else if ($('#imageContent').val() == '') {
			$('#imageContentDiv').text('상품 내용 입력');
			$('#imageContent').focus();
			
		}else {
			var formData = new FormData($('#uploadForm')[0]);
			
			$.ajax({
				type: 'post',
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				url:'/springMavenNCP/user/upload',
				data: formData,
				success: function(data){
					location.href='/springMavenNCP/user/uploadList';
				},
				error: function(e){
					console.log(e);
			} 
		});
		
		}
	});
});