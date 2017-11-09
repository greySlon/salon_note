class LogForm extends React.Component{
    constructor(props){
        super(props);
        this.check=this.check.bind(this);
        this.onPageReload=this.onPageReload.bind(this);
        this.state={authorizeCallback:this.props.authorizeCallback, credentials:this.props.credentials};
    }
    check(){
        let login=this.refs.login.value;
        let password=this.refs.password.value;
        //TODO check authority
        console.log("LogForm#check.begin: "+login+"-"+password);
        {
        this.state.credentials.login=login;
        this.state.credentials.password=password;
        this.state.credentials.role=(login==="qwerty")?"ROLE_ADMIN":"ROLE_MASTER";
        sessionStorage.setItem('credentials', JSON.stringify(this.state.credentials));
        }

        this.state.authorizeCallback(this.state.credentials);
        console.log("LogForm#check.end");
    }

    onPageReload(){
        let credentials = sessionStorage.getItem('credentials');
        console.log(credentials);
        if(credentials){
            credentials = JSON.parse(credentials);
            this.state.credentials.login=credentials.login;
            this.state.credentials.password=credentials.password;
            this.state.credentials.role=credentials.role;
            this.state.authorizeCallback();
            return true;
        }
    }

    render(){
        if(this.onPageReload()){
            return "";
        }
        return <div className="login_form">
               <input ref="login" type="text" placeholder="login" />
               <input ref="password" type="password" placeholder="password" />
               <input type="button" onClick={this.check} value="Send" />
               </div>;
    }
}
