$(function () {
  //scroll 浜嬩欢閫傜敤浜庢墍鏈夊彲婊氬姩鐨勫厓绱犲拰 window 瀵硅薄锛堟祻瑙堝櫒绐楀彛锛夈€�
  $(window).scroll(function () {
    var scroHei = $(window).scrollTop() //婊氬姩鐨勯珮搴�
    if (scroHei > 500) {
      $('.back-to-top').css('top', '-200px')
      // $('.back-to-top').fadeIn();
    } else {
      $('.back-to-top').css('top', '-999px')
      // $('.back-to-top').fadeOut();
    }
  })
  /*鐐瑰嚮杩斿洖椤堕儴*/
  $('.back-to-top').click(function () {
    $('body,html').animate(
      {
        scrollTop: 0,
      },
      600,
    )
  })
})
