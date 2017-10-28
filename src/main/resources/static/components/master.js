class Master extends React.Component{
    constructor(props){
        super(props);
        this.state={credentials:this.props.credentials,
          masters:this.props.masters,
          schedule:false};
        this.renderList=this.renderList.bind(this);
        this.onClick=this.onClick.bind(this);
        console.log("Master#constructor:"+this.state.masters);
    }
    className:"master_photo";
    onClick(e){
        alert(e.target.dataset.id);
        this.setState({schedule:true});
    }
    renderList(item,i){
        return <img src="img/photo.jpg" onClick={this.onClick} data-id={item.id}  />;
    }

    render(){
        console.log("Master#render:"+this.state.masters.length);
        this.className=(this.state.credentials.role)?"master_photo":"hide";

        return <div>
                  <div className={this.className}>
                      {this.state.masters.map(this.renderList)}
                  </div>
                  <Schedule />
               </div>
    }
}