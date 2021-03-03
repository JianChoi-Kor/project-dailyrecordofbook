/**
 * 
 */

ClassicEditor.create(document.querySelector('#editor'), {
		ckfinder : {
			uploadUrl : 'http://localhost:8080/board/imgUpload'
		}
	}).then(editor => {
		window.editor = editor
		})
	.catch(error => {
		console.error(error)
	})
	
	
	
//

