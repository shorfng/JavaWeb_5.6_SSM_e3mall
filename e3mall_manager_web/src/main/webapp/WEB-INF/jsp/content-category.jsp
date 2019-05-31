<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--
  User：蓝田_Loto
  Date：2019-05-30 23:29
  PageName：content-category.jsp
  Function：内容分类管理（菜单的添加、删除、重命名）
--%>

<div>
    <ul id="contentCategory" class="easyui-tree"></ul>
</div>

<%-- 右键点击菜单会调用menuHandler方法 --%>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>

<script type="text/javascript">
    $(function () {
        // 初始化参数
        $("#contentCategory").tree({
            url: '/content/category/list',  // 异步请求
            animate: true,
            method: "GET",

            // onContextMenu：在右键点击节点的时候触发
            // e：事件对象  node：在哪个节点上点击的右键
            onContextMenu: function (e, node) {
                e.preventDefault();                   // 执行调用事件对象的默认行为
                $(this).tree('select', node.target);  // 设置选中的节点为选中状态（背景色为蓝色长条）

                // 展示选中的节点的菜单
                $('#contentCategoryMenu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            },

            // 编辑结束触发的事件
            onAfterEdit: function (node) {
                var _tree = $(this);
                if (node.id == 0) {
                    // 新增节点，存储到数据库
                    $.post("/content/category/create", {parentId: node.parentId, name: node.text}, function (data) {
                        if (data.status == 200) {
                            _tree.tree("update", {
                                target: node.target,
                                id: data.data.id
                            });
                        } else {
                            $.messager.alert('提示', '创建' + node.text + ' 分类失败!');
                        }
                    });
                } else {
                    $.post("/content/category/update", {id: node.id, name: node.text});
                }
            }
        });
    });

    function menuHandler(item) {
        var tree = $("#contentCategory");
        var node = tree.tree("getSelected");

        // 选择添加按钮
        if (item.name === "add") {
            tree.tree('append', {
                parent: (node ? node.target : null),
                data: [{
                    text: '新建分类',
                    id: 0,
                    parentId: node.id
                }]
            });

            // 添加完子节点，默认设置为可编辑状态
            var _node = tree.tree('find', 0);
            tree.tree("select", _node.target).tree('beginEdit', _node.target);
        // 选择重命名按钮
        } else if (item.name === "rename") {
            tree.tree('beginEdit', node.target);
        // 选择删除按钮
        } else if (item.name === "delete") {
            $.messager.confirm('确认', '确定删除名为 ' + node.text + ' 的分类吗？', function (r) {
                if (r) {
                    $.post("/content/category/delete/", {id: node.id}, function () {
                        tree.tree("remove", node.target);
                    });
                }
            });
        }
    }
</script>