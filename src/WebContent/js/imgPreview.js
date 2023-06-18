function main () {
    const input = document.querySelector('#input')
    const figure = document.querySelector('#figure')
    const figureImage = document.querySelector('#figureImage')
    
    input.addEventListener('change', (event) => { 
        const [file] = event.target.files

      if (file) {
        figureImage.setAttribute('src', URL.createObjectURL(file))
        figure.style.display = 'block'

        //読み込んだ画像の縦横の大きいほうに合わせて拡大する
        figureImage.onload = () => { 
            const imageWidth = figureImage.naturalWidth
            const imageHeight = figureImage.naturalHeight
            let zoomSize;
            if ((imageWidth > imageHeight)) {
                zoomSize = (imageWidth / imageHeight) * 100;
            } else {
                zoomSize = (imageHeight / imageWidth) * 100;
            }
            figureImage.style.maxWidth = zoomSize + '%';
            figureImage.style.maxHeight = zoomSize + '%';
        }
      } else {
        figure.style.display = 'none'
      }
    })
  }

main()


  