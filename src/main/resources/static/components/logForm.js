class LogForm extends React.Component{
    constructor(props){
        super(props);
        this.check=this.check.bind(this);
        this.state={auth:this.props.auth, credentials:this.props.credentials};
    }
    check(){
        //TODO check authority
        console.log("LogForm="+this.refs.login.value+"-"+this.refs.password.value);
        this.state.credentials.login=this.refs.login.value;
        this.state.credentials.password=this.refs.password.value;
        //TODO
        this.state.credentials.role="ROLE_ADMIN";
        this.state.auth(true);
    }
    render(){
        return <div className="login_form">
               <input ref="login" type="text" placeholder="login" />
               <input ref="password" type="password" placeholder="password" />
               <input type="button" onClick={this.check} value="Send" />
               </div>;
    }
}
