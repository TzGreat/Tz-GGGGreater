// server.js
const express = require('express');
const axios = require('axios');
const cors = require('cors');
const path = require('path');

const app = express();
const PORT = 3000;

// 中间件
app.use(cors());
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));
// 静态文件服务 - 根目录（用于 auth.js）
app.use(express.static(__dirname));
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', 'http://localhost:3000');
    res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
    res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization');
    next();
});
// Spring Boot后端基础URL
const API_BASE_URL = 'http://localhost:8080';

// 统一的响应处理函数
const handleResponse = (response) => {
    return {
        code: response.status,
        data: response.data,
        msg: getMessageByStatus(response.status)
    };
};

const getMessageByStatus = (status) => {
    const messages = {
        200: '操作成功',
        201: '创建成功',
        204: '删除成功',
        404: '资源未找到',
        500: '服务器错误'
    };
    return messages[status] || '操作完成';
};

// 设置默认路由，重定向到登录页
app.get('/', (req, res) => {
    res.redirect('/login.html');
});

// 薪资分析相关路由
app.get('/api/analysis', async (req, res) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/analysis`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '获取薪资分析数据失败'
            });
        }
    }
});

app.get('/api/analysis/:deptId', async (req, res) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/analysis/${req.params.deptId}`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '获取部门薪资分析失败'
            });
        }
    }
});

// 部门相关路由
app.get('/api/depts', async (req, res) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/depts`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '获取部门数据失败'
            });
        }
    }
});

app.post('/api/depts', async (req, res) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/depts`, req.body);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '创建部门失败'
            });
        }
    }
});

app.put('/api/depts', async (req, res) => {
    try {
        const response = await axios.put(`${API_BASE_URL}/depts`, req.body);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '更新部门失败'
            });
        }
    }
});

app.delete('/api/depts/:id', async (req, res) => {
    try {
        const response = await axios.delete(`${API_BASE_URL}/depts/${req.params.id}`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '删除部门失败'
            });
        }
    }
});

// 员工相关路由
app.get('/api/emps', async (req, res) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/emps`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '获取员工数据失败'
            });
        }
    }
});

app.post('/api/emps', async (req, res) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/emps`, req.body);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '创建员工失败'
            });
        }
    }
});

app.put('/api/emps', async (req, res) => {
    try {
        const response = await axios.put(`${API_BASE_URL}/emps`, req.body);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '更新员工失败'
            });
        }
    }
});

app.delete('/api/emps/:id', async (req, res) => {
    try {
        const response = await axios.delete(`${API_BASE_URL}/emps/${req.params.id}`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '删除员工失败'
            });
        }
    }
});

app.get('/api/emps/:id', async (req, res) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/emps/${req.params.id}`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '查询员工失败'
            });
        }
    }
});
app.post('/api/auth/login', async (req, res) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/auth/login`, req.body);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.data?.message || '登录失败'
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '登录失败，请检查网络连接'
            });
        }
    }
});

app.post('/api/auth/register', async (req, res) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/auth/register`, req.body);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.data?.message || '注册失败'
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '注册失败，请检查网络连接'
            });
        }
    }
});

app.post('/api/auth/logout', async (req, res) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/auth/logout`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '登出失败'
            });
        }
    }
});

app.get('/api/auth/validate', async (req, res) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/auth/validate`);
        res.json(handleResponse(response));
    } catch (error) {
        if (error.response) {
            res.status(error.response.status).json({
                code: error.response.status,
                data: null,
                msg: error.response.statusText
            });
        } else {
            res.status(500).json({
                code: 500,
                data: null,
                msg: '验证token失败'
            });
        }
    }
});

// 启动服务器
app.listen(PORT, () => {
    console.log(`Node.js代理服务器运行在 http://localhost:${PORT}`);
    console.log(`请访问: http://localhost:${PORT}`);
    console.log(`系统将自动跳转到登录页面...`);
});