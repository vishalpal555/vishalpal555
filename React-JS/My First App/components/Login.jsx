import React from "react";
import { Link } from "react-router-dom";
import { useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";

export default function Login(props){
	const [username, setUsername] = useState("")
	const [password, setPassword] = useState("")
	function login(){
		axios.get(API_BASE_URL +"/users/" +username+"/"+password).then(res => {
			console.log(res.status);
		})
	}
    return (
        <div className="container">
	<div className="d-flex justify-content-center h-100">
		<div className="card">
			<div className="card-header">
				<h3>Sign In</h3>
				<div className="d-flex justify-content-end social_icon">
					<span><i className="fab fa-facebook-square"></i></span>
					<span><i className="fab fa-google-plus-square"></i></span>
					<span><i className="fab fa-twitter-square"></i></span>
				</div>
			</div>
			<div className="card-body">
				<form>
					<div className="input-group form-group">
						<div className="input-group-prepend">
							<span className="input-group-text"><i className="fas fa-user"></i></span>
						</div>
						<input value={username} onInput={e => setUsername(e.target.value)} type="text" className="form-control" placeholder="username" />
						
					</div>
					<div className="input-group form-group">
						<div className="input-group-prepend">
							<span className="input-group-text"><i className="fas fa-key"></i></span>
						</div>
						<input value={password} onInput={e => setPassword(e.target.value)} type="password" className="form-control" placeholder="password" />
					</div>
					<div className="row align-items-center remember">
						<input type="checkbox" />Remember Me
					</div>
					<div className="form-group">
						<button type="button" className="btn float-right login_btn" onClick={login}>Login</button>
					</div>
				</form>
			</div>
			<div className="card-footer">
				<div className="d-flex justify-content-center links">
					Don't have an account?<Link to="/register">Sign Up</Link>
				</div>
				<div className="d-flex justify-content-center">
					<Link to="#">Forgot your password?</Link>
				</div>
			</div>
		</div>
	</div>

</div>

    );
}