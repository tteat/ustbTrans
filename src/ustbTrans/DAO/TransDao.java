package ustbTrans.DAO;
import ustbTrans.entity.Custom;
import ustbTrans.entity.MARK;
import ustbTrans.entity.Memory;
import ustbTrans.entity.User;


public interface TransDao {
	/*
	 * Memory�෽��
	 */
	public int add_mem(Memory mem);//���һ����ʷ��¼
	public int check_mem(Memory mem);//����Ƿ������ʷ��¼(���ڷ���M_no�������ڷ���0)
	public String get_trans(int M_no);//��ȡ�û�ѡ���������
	public String get_word(Memory mem);//��ȡӢ�ĵ��ʣ���ʵ������Ӣ�Ļ����л��Ĺ���
	public int modify_mem(Memory mem);//�޸���ʷ��¼
	/*
	 * Wordbook��ķ���
	 */
	public int del_vo(String username, String v_word);//ɾ������
	public int add_vo(String username, String v_word);//�������
	public String[] get_vo(String username);//��ȡ�û���������
	/*
	 * User��ķ���
	 */
	public int add_user(User user);//����û�
	public int del_user(String username);//ɾ���û�
	public int check_login(User user);//��֤�û���¼��Ϣ
	public int modify_pwd(String username, String password, String oldpwd);//�޸�����
	public String find_pwd(String mailbox);//�һ�����
	/*
	 * Word�෽��
	 */
	public int add_word(String username, String w_word);

	/*
	 * Mark�෽��
	 */
	public float cal_mark(float score, MARK mark);//�������
	public int update_mark(float score, String w_word, String trans);//���·���
	/*
	 * Custom�෽��
	 */
	public int add_cus(Custom cus);//����Զ�������
	public String get_cus(String w_word);//��ȡ�Զ�������,����jason���� ����+����
	
	
}
