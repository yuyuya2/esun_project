<script setup>
    import { ref } from 'vue'

    // 顯示登入畫面
    const page = ref('login')

    // 登入資料
    const loginPhone = ref('')
    const loginPassword = ref('')
    const currentUserId = ref(null)

    // 註冊輸入的資料
    const registerName = ref('')
    const registerPhone = ref('')
    const registerEmail = ref('')
    const registerPassword = ref('')

    // 發文輸入內容
    const postContent = ref('')
    
    // 發文照片
    const selectedImage = ref(null)

    //放貼文
    const posts = ref([])
    function selectImage(event)
    {
        const file = event.target.files[0]
        if (file)
        {
            const reader = new FileReader()
            reader.onload = function()
            {
                selectedImage.value = reader.result
            }
            reader.readAsDataURL(file)
        }
    }

    // 註冊功能
    async function register() {

    const response = await fetch('http://localhost:8080/api/users/register', {
        method: 'POST',
        headers: 
        {
        'Content-Type': 'application/json'
        },

        body: JSON.stringify
        ({
        userName: registerName.value,
        phone: registerPhone.value,
        email: registerEmail.value,
        password: registerPassword.value
        })
    })

    const message = await response.text()

    alert(message)

    page.value = 'login'
    }
    async function login() 
    {
    const response = await fetch('http://localhost:8080/api/users/login', {
        method: 'POST',
        headers: 
        {
        'Content-Type': 'application/json'
        },

        body: JSON.stringify({
        phone: loginPhone.value,
        password: loginPassword.value
        })
    })

    const message = await response.text()
    alert('目前登入者ID：' + message)
    if (message !== '登入失敗')
    {
        currentUserId.value = message
        page.value = 'home'
        loadPosts()
    }
    else
    {
        alert('登入失敗')
    }
    }

    // 抓所有貼文
    async function loadPosts() 
    {
    const response = await fetch('http://localhost:8080/api/posts')
    posts.value = await response.json()
    }

    //發文功能
    async function addPost() 
    {
    const response = await fetch('http://localhost:8080/api/posts', {
        method: 'POST',

        headers: {
        'Content-Type': 'application/json'
        },

        body: JSON.stringify({
        userId: currentUserId.value,
        content: postContent.value,
        image: selectedImage.value

        })
    })

    const message = await response.text()
    alert(message)
    postContent.value = ''
    selectedImage.value = ''
    loadPosts()
    }
    
    async function deletePost(id) 
    {
    const response = await fetch('http://localhost:8080/api/posts/' + id, {
        method: 'DELETE'
    })

    const message = await response.text()
    alert(message)
    loadPosts()
    }

</script>

<template>
  <!-- 登入畫面 -->
  <div v-if="page === 'login'">

    <h1>社群媒體系統</h1>
    <label>輸入手機號碼：</label>
    <input type="text" v-model="loginPhone">

    <br><br>
    <label>密碼：</label>
    <input type="password" v-model="loginPassword">

    <br><br>
    <button @click="login">登入</button>    
    &nbsp;&nbsp;
    <button @click="page = 'register'">註冊</button>

  </div>

  <!-- 註冊畫面 -->
  <div v-if="page === 'register'">

    <h1>註冊帳號</h1>
    
    <label>使用者名稱：</label>
    <input type="text" v-model="registerName">
    
    <br><br>
    <label>手機號碼：</label>
    <input type="text" v-model="registerPhone">

    <br><br>
    <label>信箱：</label>
    <input type="text" v-model="registerEmail">

    <br><br>
    <label>密碼：</label>
    <input type="password" v-model="registerPassword">

    <br><br>
    <button @click="register">註冊</button>    &nbsp;&nbsp;
    <button @click="page = 'login'">回登入</button>

  </div>


  <!-- 登入後首頁 -->
  <div v-if="page === 'home'">

    <h1>我的首頁</h1>
    <h2>新增貼文</h2>
    <textarea v-model="postContent" placeholder="請輸入內容..."></textarea>

    <br><br>
    <label>選擇照片：</label>
    <input type="file" accept="image/*" @change="selectImage">

    <br><br>
    <button @click="addPost">發文</button>
    
    <hr>
    <h2>所有貼文</h2>

    <!-- 只顯示目前登入者的貼文 -->
    <div v-for="post in posts.filter(p => p.user_id == currentUserId)">
        <p>{{ post.content }}</p>
        <img
            v-if="post.image"
            :src="post.image"
            width="300"
        >

        <br>
        <button @click="deletePost(post.post_id)">刪除</button>
        <hr>
    </div>

  </div>

</template>