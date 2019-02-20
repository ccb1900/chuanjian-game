// pages/ws/ws.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    socketMsgQueue:[],
    socketTask:[],
    socketOpen: [false,false,false,false,false],
  },

  open(i) {
    let self = this
    console.log(i)
    if(!this.data.socketOpen[i]) {
      let socketTask = wx.connectSocket({
        url: 'ws://127.0.0.1:7002/websocket',
        header: {
          'content-type': 'application/json'
        },
        // protocols: ['protocol1'],
        // method: 'GET'
      })

      socketTask.onOpen((header) => {
        socketTask.onMessage((data) => {
          console.log(data)
          // const dataView = new DataView(data.data);
          // console.log(dataView)
          wx.showToast({
            title: i + 'send success',
          })
        })
        socketTask.onError((data) => {
          console.log(data)
          wx.showToast({
            title: i + 'error occur',
          })
        })
        socketTask.onClose((data) => {
          let so = this.data.socketOpen.slice(0, this.data.socketOpen.length)
          so[i] = false
          self.setData({
            socketOpen: so
          })
          console.log(data, i + 'close success')
          wx.showToast({
            title: i + 'close success',
          })

        })

        let st = this.data.socketTask.slice(0, this.data.socketTask.length)
        st[i] = socketTask
        this.setData({
          socketTask: st
        })

        let so = this.data.socketOpen.slice(0, this.data.socketOpen.length)
        so[i] = true
        self.setData({
          socketOpen: so
        })
        console.log(header)
        for (let i = 0; i < this.data.socketMsgQueue.length; i++) {
          wx.sendSocketMessage(this.data.socketMsgQueue[i])
        }

        self.setData({
          socketMsgQueue: []
        })

        wx.showToast({
          title: i + 'connected success',
        })
      })
    }
    
    // wx.onSocketOpen((header)=>{
    //   wx.onSocketMessage((data) => {
    //     console.log(data)
    //     wx.showToast({
    //       title: i +'send success',
    //     })
    //   })
    //   wx.onSocketError((data) => {
    //     console.log(data)
    //     wx.showToast({
    //       title: i +'error occur',
    //     })
    //   })
    //   wx.onSocketClose((data) => {
    //     let so = this.data.socketOpen.slice(0, this.data.socketOpen.length)
    //     so[i] = false
    //     self.setData({
    //       socketOpen: so
    //     })
    //     console.log(data, i +'close success')
    //     wx.showToast({
    //       title: i +'close success',
    //     })

    //   })
    //   let so = this.data.socketOpen.slice(0, this.data.socketOpen.length)
    //   so[i] = true
    //   self.setData({
    //     socketOpen: so
    //   })
    //   console.log(header)
    //   for (let i = 0; i < this.data.socketMsgQueue.length; i++) {
    //     wx.sendSocketMessage(this.data.socketMsgQueue[i])
    //   }

    //   self.setData({
    //     socketMsgQueue: []
    //   })

    //   wx.showToast({
    //     title: i +'connected success',
    //   })
    // })
    
  },
  open1() {
    this.open(0)
  },
  open2() {
    this.open(1)
  },
  close1(){
    this.close(0)
  },
  close2() {
    this.close(1)
  },
  send1() {
    this.send(0)
  },
  send2() {
    this.send(1)
  },
  close(i){
    if(this.data.socketOpen[i]) {
      this.data.socketTask[i].close()
    } else {
      wx.showToast({
        title: 'not open',
      })
    }
  },
  send(i){
    let message = JSON.stringify({
      type: 'msg',
      content: {
        fromUserId: i,
        toUserId: i,
        roomId:1,
        content: 'test'
      }
    })
    if (this.data.socketOpen[i]) {
      this.data.socketTask[i].send({
        data: message
      })
    } else {
      wx.showToast({
        title: 'queue',
      })
      this.data.socketMsgQueue.push({
        data: message
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})