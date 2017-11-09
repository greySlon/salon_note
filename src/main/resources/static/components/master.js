class Master extends React.Component{
    constructor(props){
        super(props);
        this.state={
          credentials:this.props.credentials,
          masters:this.props.masters,
          callback:this.props.callback
        };
        this.renderList=this.renderList.bind(this);
        this.onClick=this.onClick.bind(this);
        console.log("Master#constructor:"+this.state.masters);
    }
    className:"master_photo";

    onClick(e){
        console.log("Master#onClick:"+e.target.dataset.id);
        this.state.callback(e.target.dataset.id);
    }
    renderList(item,i){
        return <div class="master_block">
                    	<img src="img/photo.jpg" onClick={this.onClick} data-id={item.id} />
                        <div>{item.first_name}</div>
                    </div>;
    }

    render(){
        console.log("Master#render");
        this.className=(this.state.credentials.role)?"master_photo":"hide";

        return <div>
                  <div className={this.className}>
                      {this.state.masters.map(this.renderList)}
                  </div>
               </div>
    }
}