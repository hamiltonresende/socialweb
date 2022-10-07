import React from "react";

export class UserSignupPage extends React.Component {

    state = {
        displayName: ''
    }

    onChangeDisplayName = (event) => {
        const value = event.target.value;
        this.setState({displayName: value});
    }

    render() {
        return (
            <div>
                <h1>Sign Up</h1>
                <div>
                    <input 
                        placeholder="Your display name" 
                        value = {this.state.displayName} 
                        onChange={this.onChangeDisplayName} />
                </div>
                <div>
                    <input placeholder="Your username" />
                </div>
                <div>
                    <input placeholder="Your password" type="password" />
                </div>
                <div>
                    <input placeholder="Repeat your password" type="password" />
                </div>
                <div>
                    <button>Sign Up</button>
                </div>
            </div>
        )
    }
}

export default UserSignupPage;