/**
 * 
 */

// home slide 부분 js start
const bookSlideContainer = document.querySelector('.book-slide-container')
const bookSlide = document.querySelector('.book-slides')
const bookNextBtn = document.querySelector('#book-next-btn')
const bookPrevBtn = document.querySelector('#book-prev-btn')
const bookInterval = 10000

let bookSlides = document.querySelectorAll('.book-slide')
let bookIndex = 1
let bookSlideId

const bookFirstClone1 = bookSlides[0].cloneNode(true)
const bookFirstClone2 = bookSlides[1].cloneNode(true)
const bookFirstClone3 = bookSlides[2].cloneNode(true)
const bookLastClone1 = bookSlides[bookSlides.length - 1].cloneNode(true)
const bookLastClone2 = bookSlides[bookSlides.length - 2].cloneNode(true)
const bookLastClone3 = bookSlides[bookSlides.length - 3].cloneNode(true)


bookFirstClone1.id = 'first-clone'
bookLastClone1.id = 'last-clone'

bookSlide.append(bookFirstClone1)
// bookSlide.append(bookFirstClone2)
// bookSlide.append(bookFirstClone3)

bookSlide.prepend(bookLastClone1)
// bookSlide.prepend(bookLastClone2)
// bookSlide.prepend(bookLastClone3)



const bookSlideWidth = bookSlides[bookIndex].clientWidth


bookSlide.style.transform = `translateX(${-bookSlideWidth * bookIndex}px)`

console.log(bookSlides)

const bookStartSlide = () => {
    bookSlideId = setInterval(() => {
        bookMoveToNextSlide()
    }, bookInterval)
}

const bookGetSlides = () => document.querySelectorAll('.book-slide')

bookSlide.addEventListener('transitionend', () => {
    bookSlides = bookGetSlides()
    if(bookSlides[bookIndex].id === bookFirstClone1.id) {
        bookSlide.style.transition = 'none'
        bookIndex = 1
        bookSlide.style.transform = `translateX(${-bookSlideWidth * bookIndex}px)`
    } 
    if(bookSlides[bookIndex].id === bookLastClone1.id) {
        bookSlide.style.transition = 'none'
        bookIndex = bookSlides.length - 2
        bookSlide.style.transform = `translateX(${-bookSlideWidth * bookIndex}px)`
    } 
})

const bookMoveToNextSlide = () => {
    bookSlides = bookGetSlides()
    if(bookIndex >= bookSlides.length -1) return
    bookIndex++
    bookSlide.style.transform = `translateX(${-bookSlideWidth * bookIndex}px)`
    bookSlide.style.transition = '17.3s'
}

const bookMoveToPreviousSlide = () => {
    if(bookIndex <= 0) return
    bookIndex--
    bookSlide.style.transform = `translateX(${-bookSlideWidth * bookIndex}px)`
    bookSlide.style.transition = '17.3s'
}



bookSlideContainer.addEventListener('mouseleave', bookStartSlide)

bookNextBtn.addEventListener('click', bookMoveToNextSlide)
bookPrevBtn.addEventListener('click', bookMoveToPreviousSlide)



bookStartSlide()


// home slide부분 js end

