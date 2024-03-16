import { EllipsisOutlined, PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns } from '@ant-design/pro-components';
import { ProTable, TableDropdown } from '@ant-design/pro-components';
import { Button, Dropdown, Space, Tag } from 'antd';
import { useRef } from 'react';
import request from 'umi-request';
import {searchUser} from "@/services/ant-design-pro/api";
export const waitTimePromise = async (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

export const waitTime = async (time: number = 100) => {
  await waitTimePromise(time);
};

// type GithubIssueItem = {
//   url: string;
//   id: number;
//   number: number;
//   title: string;
//   labels: {
//     name: string;
//     color: string;
//   }[];
//   state: string;
//   comments: number;
//   created_at: string;
//   updated_at: string;
//   closed_at?: string;
// };

const columns: ProColumns<API.CurrentUser>[] = [
  {
    dataIndex: 'id',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '用户名',
    dataIndex: 'username',
    copyable: true,
  },
  {
    title: '用户账户',
    dataIndex: 'userAccount',
    copyable: true,
  },
  {
    title: '头像',
    dataIndex: 'avatarUrl',
    render: (_, record) => (
        <div>
          <img src={record.avatarUrl} width={50} />
        </div>
    ),
  },
  {
    title: '性别',
    dataIndex: 'gender',
  },
  {
    title: '电话',
    dataIndex: 'phone',
    copyable: true,
  },
  {
    title: '邮件',
    dataIndex: 'email',
    copyable: true,
  },
  {
    title: '状态',
    dataIndex: 'userStatus',
  },
  {
    title: '星球编号',
    dataIndex: 'planetCode',
  },
  {
    title: '角色',
    dataIndex: 'userRole',
    valueType: 'select',
    valueEnum: {
      0: { text: '普通用户', status: 'Default' },
      1: {
        text: '管理员',
        status: 'Success',
      },
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'dateTime',
  },
  // {
  //   title: '创建时间',
  //   key: 'showTime',
  //   dataIndex: 'created_at',
  //   valueType: 'date',
  //   sorter: true,
  //   hideInSearch: true,
  // },
  // {
  //   title: '创建时间',
  //   dataIndex: 'created_at',
  //   valueType: 'dateRange',
  //   hideInTable: true,
  //   search: {
  //     transform: (value) => {
  //       return {
  //         startTime: value[0],
  //         endTime: value[1],
  //       };
  //     },
  //   },
  // },
  {
    title: '操作',
    valueType: 'option',
    key: 'option',
    render: (text, record, _, action) => [
      <a
          key="editable"
          onClick={() => {
            action?.startEditable?.(record.id);
          }}
      >
        编辑
      </a>,
      <a href={record.avatarUrl} target="_blank" rel="noopener noreferrer" key="view">
        查看
      </a>,
      <TableDropdown
          key="actionGroup"
          onSelect={() => action?.reload()}
          menus={[
            { key: 'copy', name: '复制' },
            { key: 'delete', name: '删除' },
          ]}
      />,
    ],
  },
];

export default () => {
  const actionRef = useRef<ActionType>();
  return (
      <ProTable<API.CurrentUser>
          columns={columns}
          actionRef={actionRef}
          cardBordered
          request={async (params, sort, filter) => {
            console.log(sort, filter);
            const userList=await searchUser();
            return {
              data: userList
            }
          }}
          editable={{
            type: 'multiple',
          }}
          columnsState={{
            persistenceKey: 'pro-table-singe-demos',
            persistenceType: 'localStorage',
            defaultValue: {
              option: { fixed: 'right', disable: true },
            },
            onChange(value) {
              console.log('value: ', value);
            },
          }}
          rowKey="id"
          search={{
            labelWidth: 'auto',
          }}
          options={{
            setting: {
              listsHeight: 400,
            },
          }}
          form={{
            // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
            syncToUrl: (values, type) => {
              if (type === 'get') {
                return {
                  ...values,
                  created_at: [values.startTime, values.endTime],
                };
              }
              return values;
            },
          }}
          pagination={{
            pageSize: 5,
            onChange: (page) => console.log(page),
          }}
          dateFormatter="string"
          headerTitle="高级表格"
          // toolBarRender={() => [
          //   <Button
          //       key="button"
          //       icon={<PlusOutlined />}
          //       onClick={() => {
          //         actionRef.current?.reload();
          //       }}
          //       type="primary"
          //   >
          //     新建
          //   </Button>,
          //   <Dropdown
          //       key="menu"
          //       menu={{
          //         items: [
          //           {
          //             label: '1st item',
          //             key: '1',
          //           },
          //           {
          //             label: '2nd item',
          //             key: '1',
          //           },
          //           {
          //             label: '3rd item',
          //             key: '1',
          //           },
          //         ],
          //       }}
          //   >
          //     <Button>
          //       <EllipsisOutlined />
          //     </Button>
          //   </Dropdown>,
          // ]}
      />
  );
};
