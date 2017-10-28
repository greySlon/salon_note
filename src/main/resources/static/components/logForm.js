class LogForm extends React.Component{
    constructor(props){
        super(props);
        this.check=this.check.bind(this);
        this.state={auth:this.props.auth, credentials:this.props.credentials, className:"login_form"};
    }
    check(){
        let login=this.refs.login.value;
        let password=this.refs.password.value;
        //TODO check authority
        console.log("LogForm#check.begin: "+login+"-"+password);
        this.state.credentials.login=login;
        this.state.credentials.password=password;

        if(login==="qwerty")
            this.state.credentials.role="ROLE_ADMIN";
         else
            this.state.credentials.role="ROLE_MASTER";
        this.state.auth(this.state.credentials);
        this.setState({className:"hide"});
        console.log("LogForm#check.end");
    }

    render(){

        return <div className={this.state.className}>
               <input ref="login" type="text" placeholder="login" />
               <input ref="password" type="password" placeholder="password" />
               <input type="button" onClick={this.check} value="Send" />
               </div>;
    }
}
